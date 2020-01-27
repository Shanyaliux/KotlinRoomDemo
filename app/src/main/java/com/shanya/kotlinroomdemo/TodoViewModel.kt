package com.shanya.kotlinroomdemo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TodoViewModel(application: Application): AndroidViewModel(application) {

    private val repository: TodoRepository
    val allTodo: LiveData<List<Todo>>

    init {
        val wordsDao = TodoDatabase.getDatabase(application,viewModelScope).todoDao()
        repository = TodoRepository(wordsDao)
        allTodo = repository.allTodo
    }

    fun insert(todo: Todo) = viewModelScope.launch {
        repository.insert(todo)
    }
}