package com.example.todolist.room


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todolist.model.ToDoItem
@Dao
interface ToDoDao {
    @Query("SELECT * FROM todoitem")
    fun getAll(): List<ToDoItem>

    @Insert
    fun insertItem(toDoItem: ToDoItem)

    @Delete
    fun deleteItem(toDoItem: ToDoItem)

    @Update
    fun updateItem(toDoItem: ToDoItem)
}