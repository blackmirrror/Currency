package ru.blackmirrror.currency.domain.usecases

import ru.blackmirrror.currency.domain.models.CurrencyItemResponse
import ru.blackmirrror.currency.domain.models.CurrencyResponse
import ru.blackmirrror.currency.domain.models.ResultState
import ru.blackmirrror.currency.domain.repositories.CurrencyRepository

class GetCurrencyUseCase(private val currencyRepository: CurrencyRepository) {
    suspend operator fun invoke(): ResultState<List<CurrencyItemResponse>> {
        return currencyRepository.getCurrency()
    }
}