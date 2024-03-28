package ru.blackmirrror.currency.data.repositories

import android.content.Context
import ru.blackmirrror.currency.data.api.ApiService
import ru.blackmirrror.currency.data.local.ConnectionSharedPreferences
import ru.blackmirrror.currency.domain.models.ClientError
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
    private val connectionSharedPrefs: ConnectionSharedPreferences
) : CurrencyRepository {

    override suspend fun getCurrency(): ResultState<CurrencyResponse> {
        return if (getInternetConnection()) {
            val response = service.getCurrency()
            if (response.isSuccessful) {
                connectionSharedPrefs.connection = true
                connectionSharedPrefs.lastLoading = Date().time
                val body = response.body()
                if (body != null)
                    ResultState.Success(body)
                else
                    ResultState.Error(NoContent)
            }
            else if (response.code() in 400..499)
                ResultState.Error(ClientError)
            else
                ResultState.Error(ServerError)
        } else {
            connectionSharedPrefs.connection = false
            ResultState.Error(NoInternet)
        }
    }

    override fun getInternetConnection(): Boolean {
        val res = NetworkUtils.isInternetConnected(context)
        return res
    }

    override fun getLastLoadDate(): Long {
        return connectionSharedPrefs.lastLoading
    }
}