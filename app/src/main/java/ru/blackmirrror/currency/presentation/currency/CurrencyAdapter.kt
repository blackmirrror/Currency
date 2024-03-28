package ru.blackmirrror.currency.presentation.currency

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.blackmirrror.currency.R
import ru.blackmirrror.domain.models.CurrencyItemResponse

class CurrencyAdapter: ListAdapter<CurrencyItemResponse, CurrencyAdapter.CurrencyViewHolder>(CurrencyItemCallback()) {

    class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.tv_name)
        val ruName = itemView.findViewById<TextView>(R.id.tv_ru_name)
        val state = itemView.findViewById<ImageView>(R.id.iv_state)
        val currentPrice = itemView.findViewById<TextView>(R.id.tv_current_price)
        val prevPrice = itemView.findViewById<TextView>(R.id.tv_prev_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_currency, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val currency = getItem(position)
        with(holder) {
            name.text = currency.charCode
            ruName.text = currency.name
            currentPrice.text = "${currency.value} ₽"
            prevPrice.text = "${currency.previous} ₽"
            if (currency.value != null && currency.previous != null) {
                if (currency.value == currency.previous) {
                    state.visibility = View.INVISIBLE
                }
                else {
                    state.visibility = View.VISIBLE
                    if (currency.value!! > currency.previous!!)
                        state.setImageResource(R.drawable.ic_high)
                    else
                        state.setImageResource(R.drawable.ic_low)
                }
            }
        }
    }
}