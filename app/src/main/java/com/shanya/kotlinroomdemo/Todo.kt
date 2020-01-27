package com.shanya.kotlinroomdemo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_database")
data class Todo (
    @PrimaryKey(autoGenerate = true) val id:Int,
    @ColumnInfo(name = "todo_title") val title:String,
    @ColumnInfo(name = "todo_content") val content:String

)
