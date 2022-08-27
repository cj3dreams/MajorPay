package com.cj3dreams.majorpay.repo

import com.cj3dreams.majorpay.model.card.Result
import com.cj3dreams.majorpay.source.local.LocalSource
import com.cj3dreams.majorpay.source.remote.RemoteSource

class DataRepositoryImpl(
    private val localDataSource: LocalSource,
    private val remoteDataSource: RemoteSource): DataRepository {
    override suspend fun getAllCards() = remoteDataSource.getAllCards()
    override suspend fun getAllCardFromLocal() = localDataSource.getAllCardsFromLocal()
}