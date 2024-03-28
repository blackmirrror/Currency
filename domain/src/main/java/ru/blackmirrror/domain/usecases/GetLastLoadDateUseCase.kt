package ru.blackmirrror.domain.usecases

import ru.blackmirrror.domain.repositories.CurrencyRepository

class GetLastLoadDateUseCase(private val currencyRepository: CurrencyRepository) {
    operator fun invoke(): Long {
        return currencyRepository.getLastLoadDate()
    }
}