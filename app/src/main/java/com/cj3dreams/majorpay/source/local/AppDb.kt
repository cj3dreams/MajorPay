package com.cj3dreams.majorpay.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cj3dreams.majorpay.model.card.Result

@Database(entities = [Result::class], version = 1, exportSchema = false)
abstract class AppDb: RoomDatabase() {

    abstract fun walletDao(): WalletDao?

}