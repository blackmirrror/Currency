package ru.blackmirrror.currency.domain.repositories

import ru.blackmirrror.currency.domain.models.CurrencyResponse
import ru.blackmirrror.currency.domain.models.ResultState

interface CurrencyRepository {
    suspend fun getCurrency(): ResultState<CurrencyResponse>
    fun getInternetConnection(): Boolean
    fun getLastLoadDate(): Long
}