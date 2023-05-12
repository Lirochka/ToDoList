package com.example.todolist

import com.example.todolist.model.ToDoItem

interface RoomManager {

    fun getAll() : List<ToDoItem>
    fun insertItem(item: ToDoItem)
    fun updateItem(item: ToDoItem)
    fun deleteItem(item: ToDoItem)
}