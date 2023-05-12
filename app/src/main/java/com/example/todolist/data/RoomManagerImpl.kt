package com.example.todolist.data

import android.content.Context
import androidx.room.Room
import com.example.todolist.RoomManager
import com.example.todolist.model.ToDoItem
import com.example.todolist.room.AppDatabase


/**
 * Manage that handles logic with room data base
 */
class RoomManagerImpl(private val context: Context) : RoomManager {
    private var db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, DATABASE_NAME
    )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    override fun getAll() : List<ToDoItem>{
     return db.toDoDao().getAll()
    }

    override fun insertItem(item: ToDoItem) {
        db.toDoDao().insertItem(item)
    }

    override fun updateItem(item: ToDoItem) {
        db.toDoDao().updateItem(item)
    }

    override fun deleteItem(item: ToDoItem) {
        db.toDoDao().deleteItem(item)
    }

    companion object {
        private const val DATABASE_NAME = "database-name"
    }
}