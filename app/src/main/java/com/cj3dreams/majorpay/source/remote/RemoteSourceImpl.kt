package com.cj3dreams.majorpay.source.remote


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteSourceImpl(private val api: RemoteApiRequest): RemoteSource {
    override suspend fun getAllCards(): Result<List<com.cj3dreams.majorpay.model.card.Result>> = withContext(Dispatchers.IO){
        try {
            val response = api.getAllCards()
            if (response.isSuccessful) return@withContext Result.Success(response.body()?.results!!)

            else return@withContext Result.Error(Exception(response.message()))
        }catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }
}