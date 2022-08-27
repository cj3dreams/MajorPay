package com.cj3dreams.majorpay.repo

import com.cj3dreams.majorpay.source.remote.Result


interface DataRepository {

    suspend fun getAllCards(): Result<List<com.cj3dreams.majorpay.model.card.Result>>
    suspend fun getAllCardFromLocal(): List<com.cj3dreams.majorpay.model.card.Result>
}