package com.example.phase3project2practice.injection

import android.content.Context
import androidx.room.Room
import com.example.phase3project2practice.constants.Constants.BASE_URL
import com.example.phase3project2practice.constants.Constants.DOG_DATABASE_NAME
import com.example.phase3project2practice.database.DogDatabase
import com.example.phase3project2practice.network.DogPicApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDogDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        DogDatabase::class.java,
        DOG_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideDogDao(db: DogDatabase) = db.getDogDao()

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideOkhttpInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideDogImageApi(
        moshi: Moshi,
        networkLoggingInterceptor: HttpLoggingInterceptor
    ): DogPicApiService {

        return Retrofit.Builder()
            .client(OkHttpClient.Builder().addInterceptor(networkLoggingInterceptor).build())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()
            .create(DogPicApiService::class.java)

    }

}