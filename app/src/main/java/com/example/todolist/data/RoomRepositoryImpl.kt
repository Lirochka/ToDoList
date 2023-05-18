package com.example.todolist.data

import com.example.todolist.RoomRepository
import com.example.todolist.model.ToDoItem
import com.example.todolist.room.ToDoDao
import javax.inject.Inject

/**
 * Manage that handles logic with room data base
 */
class RoomRepositoryImpl @Inject constructor(
    private val toDoDao: ToDoDao)
    : RoomRepository {

    override fun getAll() : List<ToDoItem>{
    return toDoDao.getAll()
    }
    override fun insertItem(item: ToDoItem) {
        toDoDao.insertItem(item)
    }
    override fun updateItem(item: ToDoItem) {
        toDoDao.updateItem(item)
    }
    override fun deleteItem(item: ToDoItem) {
        toDoDao.deleteItem(item)
    }
    companion object {
        const val DATABASE_NAME = "database-name"
    }
}