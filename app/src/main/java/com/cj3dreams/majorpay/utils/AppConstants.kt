package com.cj3dreams.majorpay.utils

import com.cj3dreams.majorpay.R
import com.cj3dreams.majorpay.model.transaction.TransactionTypeModel

object AppConstants {

    const val BASE_URL = "https://parseapi.back4app.com"
    const val appId = "0OKW9Ow1BUdDfEy7xGnQ5ScDkVMPFHlyjWA2ELv2"
    const val apiKey = "oujDBrqK4bcrGrR5iKkSjZ8vTt10ffQyHIxlf5ZM"

    val listOfTheTypeTransaction = listOf(

        TransactionTypeModel(0, R.drawable.ic_phone, "Сотовая связь"),
        TransactionTypeModel(1, R.drawable.ic_internet, "Интернет"),
        TransactionTypeModel(2, R.drawable.ic_services, "Комунальные услуги"),
        TransactionTypeModel(3, R.drawable.ic_bank, "Банк и кошелки"),
        TransactionTypeModel(4, R.drawable.ic_game, "Онлайн-игры"),
        TransactionTypeModel(5, R.drawable.ic_others, "Другое"),

    )

    val listOfTheMobilePayment = listOf(

        TransactionTypeModel(100, R.mipmap.megafon, "Megafon"),
        TransactionTypeModel(101, R.mipmap.tcell, "Tcell"),
        TransactionTypeModel(102, R.mipmap.zet_mobile, "Zet-mobile"),
        TransactionTypeModel(103, R.mipmap.babilon_m, "Babilon-m"),
        TransactionTypeModel(104, R.mipmap.o_mobile, "O-Mobile"),
        TransactionTypeModel(105, R.mipmap.unknown_mob, "Unknown-Mob"),

        )
}