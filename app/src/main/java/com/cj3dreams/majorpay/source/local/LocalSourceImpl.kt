package com.cj3dreams.majorpay.source.local

import com.cj3dreams.majorpay.model.card.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LocalSourceImpl(private val dao: WalletDao): LocalSource {

    override fun getAllCardsFromLocal(): Flow<List<Result>> = dao.getAllCards()

    override suspend fun saveAllCardsToLocal(cardList: List<Result>) =
        withContext(Dispatchers.IO){ dao.saveAllCard(cardList) }

    override fun getAllHistoryFromLocal(): Flow<List<com.cj3dreams.majorpay.model.history.Result>> =
        dao.getAllHistory()

    override suspend fun saveAllHistoryToLocal(historyList:
    List<com.cj3dreams.majorpay.model.history.Result>) =
        dao.saveAllHistory(historyList)
}