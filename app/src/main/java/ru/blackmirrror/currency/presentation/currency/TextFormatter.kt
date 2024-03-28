package ru.blackmirrror.currency.presentation.currency

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class TextFormatter {
    companion object {
        fun formatLoadDate(dateLong: Long): String {
            if (dateLong != 0L) {
                val sdf = SimpleDateFormat("dd.MM.yy HH:mm")
                return "Последняя загрузка: ${sdf.format(addThreeHours(Date(dateLong)))}"
            }
            return "Вы еще не загружали данные"
        }

        fun formatCurrentDate(date: Date): String {
            val sdf = SimpleDateFormat("dd.MM.yy")
            return "Текущая дата: ${sdf.format(addThreeHours(date))}"
        }

        private fun addThreeHours(date: Date): Date {
            val calendar = Calendar.getInstance()
            calendar.time = date
            calendar.add(Calendar.HOUR_OF_DAY, 3)
            return calendar.time
        }
    }
}