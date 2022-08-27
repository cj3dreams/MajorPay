package com.cj3dreams.majorpay.source.local

import com.cj3dreams.majorpay.model.card.Result

interface LocalSource {
    suspend fun getAllCardsFromLocal(): List<Result>
}