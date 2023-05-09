package com.example.todolist.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todolist.ToDoItem

@Dao
interface ToDoDao {
    @Query ("SELECT * FROM todoitem") //достать и показать
    fun getAll(): LiveData<List<ToDoItem>>

    @Insert
    fun insertItem(toDoItem: ToDoItem)

    @Delete
    fun deleteItem(toDoItem: ToDoItem)

    @Update
    fun updateItem(toDoItem: ToDoItem)
}