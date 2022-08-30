package com.cj3dreams.majorpay.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cj3dreams.majorpay.model.card.Result
import kotlinx.coroutines.flow.Flow

@Dao
interface WalletDao {
    @Query("SELECT * FROM cards ORDER by createdAt")
    fun getAllCards(): Flow<List<Result>>

    @Query("SELECT * FROM history ORDER by createdAt DESC")
    fun getAllHistory(): Flow<List<com.cj3dreams.majorpay.model.history.Result>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllCard(cardList: List<Result>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllHistory(historyList: List<com.cj3dreams.majorpay.model.history.Result>)
}