package com.cj3dreams.majorpay.source.remote

interface RemoteSource {
    suspend fun getAllCards(): Result<List<com.cj3dreams.majorpay.model.card.Result>>
}