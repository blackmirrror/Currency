package ru.blackmirrror.currency.app.di

import org.koin.dsl.module
import ru.blackmirrror.data.api.ApiFactory
import ru.blackmirrror.data.api.ApiService
import ru.blackmirrror.data.local.ConnectionSharedPreferences
import ru.blackmirrror.data.local.room.CurrencyDb
import ru.blackmirrror.data.local.room.CurrencyDbFactory
import ru.blackmirrror.data.repositories.CurrencyRepositoryImpl
import ru.blackmirrror.domain.repositories.CurrencyRepository

val dataModule = module {
    single<CurrencyRepository> {
        CurrencyRepositoryImpl(
            context = get(),
            service = get(),
            connectionSharedPrefs = get(),
            database = get()
        )
    }

    single<ApiService> {
        ApiFactory.create()
    }

    factory {
        ConnectionSharedPreferences(context = get())
    }

    single<CurrencyDb> {
        CurrencyDbFactory.create(context = get())
    }
}