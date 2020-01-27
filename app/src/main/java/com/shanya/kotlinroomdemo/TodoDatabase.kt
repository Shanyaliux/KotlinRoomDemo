package com.shanya.kotlinroomdemo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Todo::class),version = 1,exportSchema = false)
abstract class TodoDatabase: RoomDatabase() {

    abstract fun todoDao(): TodoDao

    private class TodoDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var todoDao = database.todoDao()

                    // Delete all content here.
                    todoDao.deleteAll()

                    // Add sample todos.
                    var todo = Todo(0,"title","content")
                    todoDao.insert(todo)
                    todo = Todo(0,"title1","content1")
                    todoDao.insert(todo)

                    // TODO: Add your own words!
                    todo = Todo(0,"title2","content2")
                    todoDao.insert(todo)
                }
            }
        }
    }

    companion object{
        @Volatile
        private var INSTANCE: TodoDatabase? = null

        fun getDatabase(context: Context,scope: CoroutineScope): TodoDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    "todo_database"
                ).addCallback(TodoDatabaseCallback(scope)).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}