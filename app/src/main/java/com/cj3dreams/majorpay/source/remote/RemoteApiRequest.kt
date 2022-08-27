package com.cj3dreams.majorpay.source.remote

import com.cj3dreams.majorpay.model.card.CardsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

const val appId = "0OKW9Ow1BUdDfEy7xGnQ5ScDkVMPFHlyjWA2ELv2"
const val apiKey = "oujDBrqK4bcrGrR5iKkSjZ8vTt10ffQyHIxlf5ZM"

interface RemoteApiRequest {

    @Headers(
        "X-Parse-Application-Id: $appId",
        "X-Parse-REST-API-Key: $apiKey",
        "Content-Type: application/json")
    @GET("/classes/Cards")
    suspend fun getAllCards(): Response<CardsResponse>
}