package com.cj3dreams.majorpay.model.history

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "history", indices = [Index(value = ["objectId"], unique = true)])
data class Result(
    @ColumnInfo(name = "amount")
    val amount: String,
    @ColumnInfo(name = "category")
    val category: String,
    @ColumnInfo(name = "createdAt")
    val createdAt: String,
    @ColumnInfo(name = "icon")
    val icon: String,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "objectId")
    val objectId: String,
    @ColumnInfo(name = "to")
    val to: String,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "updatedAt")
    val updatedAt: String
)