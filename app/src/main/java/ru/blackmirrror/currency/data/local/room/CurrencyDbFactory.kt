package ru.blackmirrror.currency.data.local.room

import android.content.Context
import androidx.room.Room

object CurrencyDbFactory {
    fun create(context: Context): CurrencyDb {
        return Room.databaseBuilder(
            context,
            CurrencyDb::class.java,
            "currency_database"
        ).build()
    }
}