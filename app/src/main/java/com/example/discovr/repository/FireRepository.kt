package com.example.discovr.repository

import com.example.discovr.data.DataOrException
import com.example.discovr.model.MBook
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FireRepository @Inject constructor(
    private val queryBooks: Query
) {

    suspend fun getAllBooksFromDatabase(): DataOrException<List<MBook>, Boolean, Exception> {

        val dataOrException = DataOrException<List<MBook>, Boolean, Exception>()

        try {

            dataOrException.loading = true
            //In order to get the await, we need to add the: implementation "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.5.x" to gradle
            dataOrException.data = queryBooks.get().await().documents.map { documentSnapshot ->
                documentSnapshot.toObject(MBook::class.java)!!
            }
            if (!dataOrException.data.isNullOrEmpty()) dataOrException.loading = false
            // Log.d("Inside", "getAllBooksFromDatabase: ${dataOrException.data?.toList()}")
        }catch (e: FirebaseFirestoreException){
            dataOrException.e = e
        }
        return dataOrException
    }
}