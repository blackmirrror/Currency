package ru.blackmirrror.domain.usecases

import ru.blackmirrror.domain.models.CurrencyItemResponse
import ru.blackmirrror.domain.models.ResultState
import ru.blackmirrror.domain.repositories.CurrencyRepository

class GetCurrencyUseCase(private val currencyRepository: CurrencyRepository) {
    suspend operator fun invoke(): ResultState<List<CurrencyItemResponse>> {
        return currencyRepository.getCurrency()
    }
}