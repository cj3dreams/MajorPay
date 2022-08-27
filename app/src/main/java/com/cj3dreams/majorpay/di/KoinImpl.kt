package com.cj3dreams.majorpay.di

import android.app.Application
import androidx.room.Room
import com.cj3dreams.majorpay.repo.DataRepositoryImpl
import com.cj3dreams.majorpay.source.local.AppDb
import com.cj3dreams.majorpay.source.local.LocalSourceImpl
import com.cj3dreams.majorpay.source.local.WalletDao
import com.cj3dreams.majorpay.source.remote.RemoteApiRequest
import com.cj3dreams.majorpay.source.remote.RemoteSourceImpl
import com.cj3dreams.majorpay.vm.HomeViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://parseapi.back4app.com"

val networkModule = module {
    fun <Api> provideRetrofit(api: Class<Api>) =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().also { client ->
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(logging)
            }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)

    factory { provideRetrofit(RemoteApiRequest::class.java) }
}
val dataBase = module {

    fun provideDataBase(application: Application) =
        Room.databaseBuilder(application, AppDb::class.java, "AppDb")
            .fallbackToDestructiveMigration()
            .build()

    fun provideDao(database: AppDb) = database.walletDao()

    fun provideDataRepository(dao: WalletDao, api: RemoteApiRequest) =
        DataRepositoryImpl(
            LocalSourceImpl(dao = dao),
            RemoteSourceImpl(api = api)
        )

    single { provideDataBase(androidApplication()) }
    single { provideDao(get()) }
    single { provideDataRepository(get(), get()) }
    viewModel {
        HomeViewModel(get())
    }
}