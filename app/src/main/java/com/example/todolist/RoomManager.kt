package com.example.todolist

interface RoomManager {

    fun getAll() : List<ToDoItem>
    fun insertItem(item: ToDoItem)
    fun updateItem(item: ToDoItem)
    fun deleteItem(item: ToDoItem)
}