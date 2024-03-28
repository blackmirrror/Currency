package ru.blackmirrror.currency.data.repositories

import android.content.Context
import ru.blackmirrror.currency.data.api.ApiService
import ru.blackmirrror.currency.data.local.ConnectionSharedPreferences
import ru.blackmirrror.currency.data.local.room.CurrencyDb
import ru.blackmirrror.currency.data.local.room.CurrencyEntity
import ru.blackmirrror.currency.domain.models.ClientError
import ru.blackmirrror.currency.domain.models.CurrencyItemResponse
import ru.blackmirrror.currency.domain.models.CurrencyResponse
import ru.blackmirrror.currency.domain.models.NetworkUtils
import ru.blackmirrror.currency.domain.models.NoContent
import ru.blackmirrror.currency.domain.models.NoInternet
import ru.blackmirrror.currency.domain.models.ResultState
import ru.blackmirrror.currency.domain.models.ServerError
import ru.blackmirrror.currency.domain.repositories.CurrencyRepository
import java.util.Date

class CurrencyRepositoryImpl(
    private val context: Context,
    private val service: ApiService,
    private val connectionSharedPrefs: ConnectionSharedPreferences,
    private val database: CurrencyDb
) : CurrencyRepository {

    override suspend fun getCurrency(): ResultState<List<CurrencyItemResponse>> {
        return if (getInternetConnection()) {
            val response = service.getCurrency()
            if (response.isSuccessful) {
                connectionSharedPrefs.lastLoading = Date().time
                val body = response.body()
                if (body != null) {
                    val currencies = body.valute.values.toList()
                    database.movieDao()
                        .insertCurrencies(currencies.map { CurrencyEntity.fromResponseToEntity(it) })
                    ResultState.Success(currencies)
                } else
                    ResultState.Error(NoContent, getCurrencyLocal())
            } else if (response.code() in 400..499)
                ResultState.Error(ClientError, getCurrencyLocal())
            else
                ResultState.Error(ServerError, getCurrencyLocal())
        } else {
            ResultState.Error(NoInternet, getCurrencyLocal())
        }
    }

    private suspend fun getCurrencyLocal(): List<CurrencyItemResponse> {
        return database.movieDao().getAllCurrencies()
            .map { CurrencyEntity.fromEntityToResponse(it) }
    }

    override fun getInternetConnection(): Boolean {
        return NetworkUtils.isInternetConnected(context)
    }

    override fun getLastLoadDate(): Long {
        return connectionSharedPrefs.lastLoading
    }
}