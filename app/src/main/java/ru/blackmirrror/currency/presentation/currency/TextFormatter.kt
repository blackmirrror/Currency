package ru.blackmirrror.currency.presentation.currency

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class TextFormatter {
    companion object {
        fun formatLoadDate(dateLong: Long): String {
            if (dateLong != 0L) {
                val sdf = SimpleDateFormat("dd.MM.yy HH:mm")
                return "Последняя загрузка: ${sdf.format(Date(dateLong))}"
            }
            return "Вы еще не загружали данные"
        }

        fun formatCurrentDate(date: Date): String {
            val sdf = SimpleDateFormat("dd.MM.yy")
            return "Текущая дата: ${sdf.format(date)}"
        }
    }
}