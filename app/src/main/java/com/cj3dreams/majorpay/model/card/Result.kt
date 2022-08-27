package com.cj3dreams.majorpay.model.card

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "cards", indices = [Index(value = ["objectId"], unique = true)])
data class Result(

    @ColumnInfo(name = "balance")
    val balance: String,
    @ColumnInfo(name = "belongingCard")
    val belongingCard: String,
    @ColumnInfo(name = "createdAt")
    val createdAt: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "numbers")
    val numbers: String,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "objectId")
    val objectId: String,
    @ColumnInfo(name = "updatedAt")
    val updatedAt: String
)