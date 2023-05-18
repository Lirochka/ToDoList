package com.example.todolist

import com.example.todolist.model.ToDoItem
interface RoomRepository {

    /**
     * Return list todo from Room
     */
    fun getAll() : List<ToDoItem>

    /**
     * Insert item todo in Room
     */
    fun insertItem(item: ToDoItem)

    /**
     * Update item todo in Room
     */
    fun updateItem(item: ToDoItem)

    /**
     * Delete item todo from Room
     */
    fun deleteItem(item: ToDoItem)
}