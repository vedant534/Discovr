package com.example.discovr.repository

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.discovr.model.Item
import com.example.discovr.network.BooksApi
import com.example.discovr.utils.Resource
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val api: BooksApi
                                        ) {
    suspend fun getBooks(searchQuery: String): Resource<List<Item>> {
        return try {
            val itemList = api.getAllBooks(searchQuery).items
            Log.d("ItemList", "success ${itemList.size}")
            Resource.Success(data = itemList)
        }catch (exception:Exception){
            Log.d("BookList","failure ")
            Resource.Error(message = exception.message.toString())

        }
    }

    suspend fun getBookInfo(bookId: String): Resource<Item> {
        val response = try {
            Resource.Loading(data = true)
            api.getBookInfo(bookId)


        }catch (e: Exception){
            return Resource.Error("An error occurred")
        }
        Resource.Loading(data = false)
        return Resource.Success(response)

    }

}