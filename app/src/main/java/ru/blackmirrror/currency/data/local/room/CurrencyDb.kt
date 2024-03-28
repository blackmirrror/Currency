package ru.blackmirrror.currency.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CurrencyEntity::class], version = 1, exportSchema = true)
abstract class CurrencyDb : RoomDatabase() {
    abstract fun movieDao(): CurrencyDao
}