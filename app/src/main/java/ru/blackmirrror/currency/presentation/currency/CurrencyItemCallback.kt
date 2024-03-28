package ru.blackmirrror.currency.presentation.currency

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import ru.blackmirrror.currency.domain.models.CurrencyItemResponse

class CurrencyItemCallback: ItemCallback<CurrencyItemResponse>() {
    override fun areItemsTheSame(oldItem: CurrencyItemResponse, newItem: CurrencyItemResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CurrencyItemResponse, newItem: CurrencyItemResponse): Boolean {
        return oldItem == newItem
    }
}