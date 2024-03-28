package ru.blackmirrror.currency.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.blackmirrror.currency.app.di.appModule
import ru.blackmirrror.currency.app.di.dataModule
import ru.blackmirrror.currency.app.di.domainModule

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(dataModule, domainModule, appModule)
        }
    }
}