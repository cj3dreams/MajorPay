package com.cj3dreams.majorpay.repo

import com.cj3dreams.majorpay.model.card.Result
import com.cj3dreams.majorpay.source.local.LocalSource
import com.cj3dreams.majorpay.source.remote.RemoteSource
import kotlinx.coroutines.flow.Flow

class DataRepositoryImpl(
    private val localDataSource: LocalSource,
    private val remoteDataSource: RemoteSource): DataRepository {

    override suspend fun getAllCards() = remoteDataSource.getAllCards()
    override suspend fun updateCard(objectId: String, balance: String) =
        remoteDataSource.updateCard(objectId, balance)
    override suspend fun getAllHistory() = remoteDataSource.getAllHistory()
    override suspend fun saveHistory(category: String, icon: String,
        amount: String, to: String, type: String) = remoteDataSource.saveHistory(category, icon, amount, to, type)

    override fun getAllCardsFromLocal() = localDataSource.getAllCardsFromLocal()
    override suspend fun saveAllCardToLocal(cardList: List<Result>) =
        localDataSource.saveAllCardsToLocal(cardList)

    override fun getAllHistoryFromLocal(): Flow<List<com.cj3dreams.majorpay.model.history.Result>> =
        localDataSource.getAllHistoryFromLocal()

    override suspend fun saveAllHistoryToLocal(historyList:
    List<com.cj3dreams.majorpay.model.history.Result>) =
        localDataSource.saveAllHistoryToLocal(historyList)
}