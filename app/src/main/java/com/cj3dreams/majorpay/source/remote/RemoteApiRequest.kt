package com.cj3dreams.majorpay.source.remote

import com.cj3dreams.majorpay.model.card.CardUpdatedResponse
import com.cj3dreams.majorpay.model.card.CardsResponse
import com.cj3dreams.majorpay.model.history.HistoryResponse
import com.cj3dreams.majorpay.model.history.SavedHistoryResponse
import com.cj3dreams.majorpay.utils.AppConstants.apiKey
import com.cj3dreams.majorpay.utils.AppConstants.appId
import retrofit2.Response
import retrofit2.http.*

interface RemoteApiRequest {

    @Headers(
        "X-Parse-Application-Id: $appId",
        "X-Parse-REST-API-Key: $apiKey",
        "Content-Type: application/json"
    )
    @GET("/classes/Cards")
    suspend fun getAllCards(): Response<CardsResponse>

    ///
    @FormUrlEncoded
    @Headers(
        "X-Parse-Application-Id: $appId",
        "X-Parse-REST-API-Key: $apiKey",
        "Content-Type: application/x-www-form-urlencoded"
    )
    @PUT("/classes/Cards/{objectId}")
    suspend fun updateCardAmount(
        @Path("objectId") objectId: String,
        @Field("balance") balance: String,
    ): Response<CardUpdatedResponse>

    ///
    @Headers(
        "X-Parse-Application-Id: $appId",
        "X-Parse-REST-API-Key: $apiKey",
        "Content-Type: application/json"
    )
    @GET("/classes/History")
    suspend fun getAllHistory(): Response<HistoryResponse>

    ///
    @FormUrlEncoded
    @Headers(
        "X-Parse-Application-Id: $appId",
        "X-Parse-REST-API-Key: $apiKey",
        "Content-Type: application/x-www-form-urlencoded"
    )
    @POST("/classes/History")
    suspend fun saveHistory(
        @Field("category") category: String,
        @Field("icon") icon: String,
        @Field("amount") amount: String,
        @Field("to") to: String,
        @Field("type") type: String
    ): Response<SavedHistoryResponse>
///
}