package com.shanya.kotlinroomdemo

import androidx.lifecycle.LiveData

class TodoRepository(private val todoDao: TodoDao) {

    val allTodo: LiveData<List<Todo>> = todoDao.getAlphabetizedTodoList()

    suspend fun insert(todo: Todo){
        todoDao.insert(todo)
    }
}