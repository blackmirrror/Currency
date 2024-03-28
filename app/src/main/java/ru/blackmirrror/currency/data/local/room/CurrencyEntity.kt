package ru.blackmirrror.currency.data.local.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.blackmirrror.currency.domain.models.CurrencyItemResponse

@Entity(tableName = "currencies")
data class CurrencyEntity(
    @PrimaryKey(autoGenerate = false) @ColumnInfo("id") val id: String = "",
    @ColumnInfo("numCode") var numCode: String? = null,
    @ColumnInfo("charCode") var charCode: String? = null,
    @ColumnInfo("nominal") var nominal: Int? = null,
    @ColumnInfo("name") var name: String? = null,
    @ColumnInfo("value") var value: Double? = null,
    @ColumnInfo("previous") var previous: Double? = null
) {
    companion object {
        fun fromResponseToEntity(response: CurrencyItemResponse): CurrencyEntity {
            return CurrencyEntity(
                id = response.id?:"",
                numCode = response.numCode,
                charCode = response.charCode,
                nominal = response.nominal,
                name = response.name,
                value = response.value,
                previous = response.previous
            )
        }
        fun fromEntityToResponse(entity: CurrencyEntity): CurrencyItemResponse {
            return CurrencyItemResponse(
                id = entity.id,
                numCode = entity.numCode,
                charCode = entity.charCode,
                nominal = entity.nominal,
                name = entity.name,
                value = entity.value,
                previous = entity.previous
            )
        }
    }
}