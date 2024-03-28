package ru.blackmirrror.currency.app.di

import org.koin.dsl.module
import ru.blackmirrror.domain.usecases.GetCurrencyUseCase
import ru.blackmirrror.domain.usecases.GetInternetConnectionUseCase
import ru.blackmirrror.domain.usecases.GetLastLoadDateUseCase

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