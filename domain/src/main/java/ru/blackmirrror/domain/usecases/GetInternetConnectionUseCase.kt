package ru.blackmirrror.domain.usecases

import ru.blackmirrror.domain.repositories.CurrencyRepository

class GetInternetConnectionUseCase(private val currencyRepository: CurrencyRepository) {
    operator fun invoke(): Boolean {
        return currencyRepository.getInternetConnection()
    }
}