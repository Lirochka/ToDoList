package com.example.todolist.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolist.model.ToDoItem

@Database(entities = [ToDoItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun toDoDao(): ToDoDao
}