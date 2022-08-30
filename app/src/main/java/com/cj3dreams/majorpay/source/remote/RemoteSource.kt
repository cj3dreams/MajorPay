package com.cj3dreams.majorpay.source.remote

import com.cj3dreams.majorpay.model.card.CardUpdatedResponse
import com.cj3dreams.majorpay.model.history.SavedHistoryResponse

interface RemoteSource {
    suspend fun getAllCards(): ResultResponse<List<com.cj3dreams.majorpay.model.card.Result>>
    suspend fun updateCard(objectId: String, balance: String): ResultResponse<CardUpdatedResponse>
    suspend fun getAllHistory(): ResultResponse<List<com.cj3dreams.majorpay.model.history.Result>>
    suspend fun saveHistory(category: String, icon: String,
        amount: String, to: String, type: String): ResultResponse<SavedHistoryResponse>
}