package com.cj3dreams.majorpay

import android.app.Application
import com.cj3dreams.majorpay.di.dataBase
import com.cj3dreams.majorpay.di.historyViewModel
import com.cj3dreams.majorpay.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(networkModule, dataBase, historyViewModel)
        }
    }
}