package com.cj3dreams.majorpay.source.remote


import com.cj3dreams.majorpay.model.card.CardUpdatedResponse
import com.cj3dreams.majorpay.model.history.SavedHistoryResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteSourceImpl(private val api: RemoteApiRequest): RemoteSource {
    override suspend fun getAllCards(): ResultResponse<List<com.cj3dreams.majorpay.model.card.Result>> =
        withContext(Dispatchers.IO) {
            try {
                val response = api.getAllCards()
                if (response.isSuccessful) return@withContext ResultResponse.Success(response.body()?.results!!)
                else return@withContext ResultResponse.Error(Exception(response.message()))
            } catch (e: Exception) {
                return@withContext ResultResponse.Error(e)
            }
        }

    override suspend fun updateCard(objectId: String, balance: String): ResultResponse<CardUpdatedResponse> =
        withContext(Dispatchers.IO) {
            try {
                val response = api.updateCardAmount(objectId, balance)
                if (response.isSuccessful) return@withContext ResultResponse.Success(response.body()!!)
                else return@withContext ResultResponse.Error(Exception(response.message()))
            } catch (e: Exception) {
                return@withContext ResultResponse.Error(e)
            }
        }

    override suspend fun getAllHistory(): ResultResponse<List<com.cj3dreams.majorpay.model.history.Result>> =
        withContext(Dispatchers.IO) {
            try {
                val response = api.getAllHistory()
                if (response.isSuccessful) return@withContext ResultResponse.Success(response.body()?.results!!)
                else return@withContext ResultResponse.Error(Exception(response.message()))
            } catch (e: Exception) {
                return@withContext ResultResponse.Error(e)
            }
        }

    override suspend fun saveHistory(
        category: String, icon: String, amount: String, to: String, type: String): ResultResponse<SavedHistoryResponse> =
        withContext(Dispatchers.IO) {
            try {
                val response = api.saveHistory(category, icon, amount, to, type)
                if (response.isSuccessful) return@withContext ResultResponse.Success(response.body()!!)
                else return@withContext ResultResponse.Error(Exception(response.message()))
            } catch (e: Exception) {
                return@withContext ResultResponse.Error(e)
            }
        }
}