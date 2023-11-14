package com.example.discovr.screens.search

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.discovr.model.Item
import com.example.discovr.repository.BookRepository
import com.example.discovr.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksSearchViewModel @Inject constructor(
    private val repository: BookRepository
                                              ): ViewModel() {
    var list: List<Item> by mutableStateOf(listOf())
    init {
        loadBooks()
    }

    private fun loadBooks() {
        searchBooks("android")
    }
    fun searchBooks(query: String) {
        viewModelScope.launch(Dispatchers.Default) {
            if (query.isEmpty()) {
                //do nothing
                return@launch
            }
            try {
                when(val response = repository.getBooks(query)) {
                    is Resource.Success -> {
                        Log.d("MainViewModel", "fetchBooks: Success")
                        list = response.data!!
                    }
                    is Resource.Error -> {
                        Log.d("TAG", "fetchBooks: Failure")
                    }
                    else -> {}
                }

            }catch (e: Exception){
                Log.d("Exce", "searchBooks: ${e.localizedMessage}")}
        }
    }


}