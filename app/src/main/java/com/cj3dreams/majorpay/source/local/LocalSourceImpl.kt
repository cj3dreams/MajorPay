package com.cj3dreams.majorpay.source.local

import com.cj3dreams.majorpay.model.card.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalSourceImpl(private val dao: WalletDao): LocalSource {
    override suspend fun getAllCardsFromLocal(): List<Result> = withContext(Dispatchers.IO){
        val allData = dao.getAllCards()
        return@withContext allData
    }

}