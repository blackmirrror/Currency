package ru.blackmirrror.data.api

import retrofit2.Response
import retrofit2.http.GET
import ru.blackmirrror.domain.models.CurrencyResponse

interface ApiService {

    @GET("daily_json.js")
    suspend fun getCurrency(): Response<CurrencyResponse>
}