package ru.blackmirrror.currency.domain.repositories

import ru.blackmirrror.currency.domain.models.CurrencyItemResponse
import ru.blackmirrror.currency.domain.models.CurrencyResponse
import ru.blackmirrror.currency.domain.models.ResultState

interface CurrencyRepository {
    suspend fun getCurrency(): ResultState<List<CurrencyItemResponse>>
    fun getInternetConnection(): Boolean
    fun getLastLoadDate(): Long
}