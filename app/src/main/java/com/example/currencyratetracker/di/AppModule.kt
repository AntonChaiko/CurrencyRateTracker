package com.example.currencyratetracker.di

import android.content.Context
import androidx.room.Room
import com.example.currencyratetracker.data.Dispatchers
import com.example.currencyratetracker.data.db.local.FavoritesDao
import com.example.currencyratetracker.data.db.local.FavoritesDatabase
import com.example.currencyratetracker.data.remote.ExchangeDataRatesApi
import com.example.currencyratetracker.data.repository.CurrencyRepositoryImpl
import com.example.currencyratetracker.data.solid.Crashlytics
import com.example.currencyratetracker.data.solid.Logger
import com.example.currencyratetracker.domain.repository.CurrencyRepository
import com.example.currencyratetracker.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideExchangeDataRatesApi(): ExchangeDataRatesApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .client(
                OkHttpClient().newBuilder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }).build()
            )
            .build()
            .create(ExchangeDataRatesApi::class.java)

    }

    @Provides
    fun provideFavoritesDatabase(@ApplicationContext app: Context): FavoritesDatabase {
        return Room.databaseBuilder(app, FavoritesDatabase::class.java, "favorites_entity").build()
    }

    @Provides
    fun provideFavoritesDao(database: FavoritesDatabase): FavoritesDao = database.dao

    @Provides
    @Named("LogInFile")
    fun provideLogInFile(): Logger = Logger.LogInFile()

    @Provides
    fun provideCrashlytics(): Crashlytics = Crashlytics()

    @Provides
    fun provideLogInCrashlytics(crashlytics: Crashlytics): Logger =
        Logger.LogInCrashlytics(crashlytics)

    @Provides
    fun provideDispatchers(): Dispatchers = Dispatchers.Io()

    @Provides
    fun provideCurrencyRepository(
        api: ExchangeDataRatesApi,
        dao: FavoritesDao,
        dispatchers: Dispatchers
    ): CurrencyRepository =
        CurrencyRepositoryImpl(api, dao, dispatchers)
}