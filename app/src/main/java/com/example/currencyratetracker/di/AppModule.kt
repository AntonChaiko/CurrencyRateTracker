package com.example.currencyratetracker.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.currencyratetracker.data.db.local.FavoritesDatabase
import com.example.currencyratetracker.data.remote.ExchangeDataRatesApi
import com.example.currencyratetracker.data.repository.CurrencyRepositoryImpl
import com.example.currencyratetracker.domain.repository.CurrencyRepository
import com.example.currencyratetracker.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


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
    fun provideCurrencyRepository(api: ExchangeDataRatesApi, db:FavoritesDatabase): CurrencyRepository =
        CurrencyRepositoryImpl(api,db)
}