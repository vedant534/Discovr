package com.example.discovr.screens.details

import androidx.lifecycle.ViewModel
import com.example.discovr.model.Item
import com.example.discovr.repository.BookRepository
import com.example.discovr.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: BookRepository) : ViewModel() {
    suspend fun getBookInfo(bookId: String): Resource<Item> {
        return repository.getBookInfo(bookId)
    }
}