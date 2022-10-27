package com.example.mealapp.di


import android.content.Context
import androidx.room.Room
import com.example.mealapp.BuildConfig
import com.example.mealapp.Constant
import com.example.mealapp.network.ApiHelper
import com.example.mealapp.network.ApiHelperImpl
import com.example.mealapp.network.ApiService
import com.example.mealapp.room.DatabaseDao
import com.example.mealapp.room.DatabaseRepository
import com.example.mealapp.room.DatabaseRepositoryImpl
import com.example.mealapp.room.MainDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideBaseUrl() = Constant.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG){
        val loggingInterceptor =HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }else{
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL:String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

    @Provides
    @Singleton
    fun provideDB(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, MainDatabase::class.java, "meal_database").build()

    @Provides
    @Singleton
    fun provideDatabaseDao(mainDatabase: MainDatabase) = mainDatabase.databaseDao()

    @Provides
    @Singleton
    fun provideDatabaseRepository(databaseDao: DatabaseDao): DatabaseRepository = DatabaseRepositoryImpl(databaseDao)

}