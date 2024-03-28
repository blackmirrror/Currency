package ru.blackmirrror.currency.app.di

import org.koin.dsl.module
import ru.blackmirrror.currency.domain.usecases.GetCurrencyUseCase
import ru.blackmirrror.currency.domain.usecases.GetInternetConnectionUseCase
import ru.blackmirrror.currency.domain.usecases.GetLastLoadDateUseCase

val domainModule = module {
    factory {
        GetCurrencyUseCase(currencyRepository = get())
    }

    factory {
        GetInternetConnectionUseCase(currencyRepository = get())
    }

    factory {
        GetLastLoadDateUseCase(currencyRepository = get())
    }
}