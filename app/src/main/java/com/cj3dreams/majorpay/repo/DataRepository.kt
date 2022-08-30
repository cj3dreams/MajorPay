package com.cj3dreams.majorpay.repo

import com.cj3dreams.majorpay.model.card.CardUpdatedResponse
import com.cj3dreams.majorpay.model.history.SavedHistoryResponse
import com.cj3dreams.majorpay.source.remote.ResultResponse
import kotlinx.coroutines.flow.Flow


interface DataRepository {

    suspend fun getAllCards(): ResultResponse<List<com.cj3dreams.majorpay.model.card.Result>>
    suspend fun updateCard(objectId: String, balance: String): ResultResponse<CardUpdatedResponse>
    suspend fun getAllHistory(): ResultResponse<List<com.cj3dreams.majorpay.model.history.Result>>
    suspend fun saveHistory(category: String, icon: String,
        amount: String, to: String, type: String): ResultResponse<SavedHistoryResponse>

    fun getAllCardsFromLocal(): Flow<List<com.cj3dreams.majorpay.model.card.Result>>
    suspend fun saveAllCardToLocal(cardList: List<com.cj3dreams.majorpay.model.card.Result>)
    fun getAllHistoryFromLocal(): Flow<List<com.cj3dreams.majorpay.model.history.Result>>
    suspend fun saveAllHistoryToLocal(historyList: List<com.cj3dreams.majorpay.model.history.Result>)
}