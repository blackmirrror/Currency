package ru.blackmirrror.currency.app.di

import org.koin.dsl.module
import ru.blackmirrror.currency.data.api.ApiFactory
import ru.blackmirrror.currency.data.api.ApiService
import ru.blackmirrror.currency.data.local.ConnectionSharedPreferences
import ru.blackmirrror.currency.data.repositories.CurrencyRepositoryImpl
import ru.blackmirrror.currency.domain.repositories.CurrencyRepository

val dataModule = module {
    single<CurrencyRepository> {
        CurrencyRepositoryImpl(
            context = get(),
            service = get(),
            connectionSharedPrefs = get()
        )
    }

    single<ApiService> {
        ApiFactory.create()
    }

    factory {
        ConnectionSharedPreferences(context = get())
    }
}