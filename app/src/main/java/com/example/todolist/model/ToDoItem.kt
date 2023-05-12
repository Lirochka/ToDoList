package com.example.todolist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDoItem(

    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo("title_column") val title: String,
    @ColumnInfo("description_column") val description: String,
    @ColumnInfo("number_column") val number: String
    )
