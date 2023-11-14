package com.example.discovr.di

import com.example.discovr.network.BooksApi
import com.example.discovr.repository.BookRepository
import com.example.discovr.repository.FireRepository
import com.example.discovr.utils.Constants
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideBookRepository(
        api: BooksApi
    ) = BookRepository(api)

    @Singleton
    @Provides
    fun provideFireBookRepository(
    ) = FireRepository(queryBooks = FirebaseFirestore.getInstance()
        .collection("books")
        //TODO: show recently added first
        /* .orderBy("title", Query.Direction.DESCENDING)*/
    )

    @Singleton
    @Provides
    fun provideBookApi(): BooksApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BooksApi::class.java)
    }

}