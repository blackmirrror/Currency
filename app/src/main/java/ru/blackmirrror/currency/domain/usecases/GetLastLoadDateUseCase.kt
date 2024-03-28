package ru.blackmirrror.currency.domain.usecases

import ru.blackmirrror.currency.domain.repositories.CurrencyRepository

class GetLastLoadDateUseCase(private val currencyRepository: CurrencyRepository) {
    operator fun invoke(): Long {
        return currencyRepository.getLastLoadDate()
    }
}