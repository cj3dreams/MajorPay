package com.cj3dreams.majorpay.source.local

import androidx.room.Dao
import androidx.room.Query
import com.cj3dreams.majorpay.model.card.Result

@Dao
interface WalletDao {
    @Query("SELECT * FROM cards ORDER by objectId DESC")
    suspend fun getAllCards(): List<Result>
}