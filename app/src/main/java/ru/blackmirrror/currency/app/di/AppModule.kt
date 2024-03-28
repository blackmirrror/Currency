package ru.blackmirrror.currency.app.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.blackmirrror.currency.presentation.currency.CurrencyViewModel

val appModule = module {
    viewModel {
        CurrencyViewModel(
            getCurrencyUseCase = get(),
            getInternetConnectionUseCase = get(),
            getLastLoadDateUseCase = get()
        )
    }
}