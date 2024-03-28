package ru.blackmirrror.domain.repositories

import ru.blackmirrror.domain.models.CurrencyItemResponse
import ru.blackmirrror.domain.models.ResultState

interface CurrencyRepository {
    suspend fun getCurrency(): ResultState<List<CurrencyItemResponse>>
    fun getInternetConnection(): Boolean
    fun getLastLoadDate(): Long
}