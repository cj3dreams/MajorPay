package com.cj3dreams.majorpay.source.local

import com.cj3dreams.majorpay.model.card.Result
import kotlinx.coroutines.flow.Flow

interface LocalSource {
    fun getAllCardsFromLocal(): Flow<List<Result>>
    suspend fun saveAllCardsToLocal(cardList: List<Result>)
    fun getAllHistoryFromLocal(): Flow<List<com.cj3dreams.majorpay.model.history.Result>>
    suspend fun saveAllHistoryToLocal(historyList: List<com.cj3dreams.majorpay.model.history.Result>)
}