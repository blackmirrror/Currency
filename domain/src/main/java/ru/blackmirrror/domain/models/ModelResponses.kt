package ru.blackmirrror.domain.models

import com.google.gson.annotations.SerializedName

data class CurrencyResponse(
    @SerializedName("Date"         ) var date         : String? = null,
    @SerializedName("PreviousDate" ) var previousDate : String? = null,
    @SerializedName("PreviousURL"  ) var previousURL  : String? = null,
    @SerializedName("Timestamp"    ) var timestamp    : String? = null,
    @SerializedName("Valute"       ) var valute       : Map<String, CurrencyItemResponse> = mapOf()
)

data class CurrencyItemResponse(
    @SerializedName("ID"       ) var id       : String? = null,
    @SerializedName("NumCode"  ) var numCode  : String? = null,
    @SerializedName("CharCode" ) var charCode : String? = null,
    @SerializedName("Nominal"  ) var nominal  : Int?    = null,
    @SerializedName("Name"     ) var name     : String? = null,
    @SerializedName("Value"    ) var value    : Double? = null,
    @SerializedName("Previous" ) var previous : Double? = null
)