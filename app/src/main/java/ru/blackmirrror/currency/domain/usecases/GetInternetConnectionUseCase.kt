package ru.blackmirrror.currency.domain.usecases

import ru.blackmirrror.currency.domain.repositories.CurrencyRepository

class GetInternetConnectionUseCase(private val currencyRepository: CurrencyRepository) {
    operator fun invoke(): Boolean {
        return currencyRepository.getInternetConnection()
    }
}