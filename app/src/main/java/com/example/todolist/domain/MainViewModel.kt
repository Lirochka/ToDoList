package com.example.todolist.domain

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todolist.RoomManager
import com.example.todolist.model.ToDoItem
import com.example.todolist.data.RoomManagerImpl

class MainViewModel(app: Application): AndroidViewModel(app) {

    private val roomManager: RoomManager = RoomManagerImpl(app)

    private var todoItemList: MutableLiveData<List<ToDoItem>> = MutableLiveData()
    val toDoItemListResult: LiveData<List<ToDoItem>> = todoItemList

    /**
     * Provides all data from room
     */
    fun getAllData() {
       val result = roomManager.getAll()
        todoItemList.postValue(result)
    }

    /**
     * Insert Item in room data base
     * @param item - provides item that need to be insert in room data base
     */
    fun insertItem(item: ToDoItem) {
        todoItemList.value.let {
            todoItemList.postValue(it?.plus(item))
            roomManager.insertItem(item)
        }
    }

    /**
     * Updated existing Item in room data base
     * @param item - provides item that need to be updated in room data base

     */
    fun updateItem(item: ToDoItem) {
        val foundItem = todoItemList.value?.indexOfFirst { it.id == item.id } // Search for needed item
        foundItem?.let {
            val list = todoItemList.value?.toMutableList()
            list?.set(it, item) //Updating the item
            todoItemList.value= list!! //Post the final list to live data
        }
    }

    /**
     * Delete existing Item from room data base
     * @param item - provides item that need to be deleted from room data base
     */
    fun deleteItem(item: ToDoItem) {
        todoItemList.value.let {
            todoItemList.postValue(it?.minus(item))
            roomManager.deleteItem(item)
        }
    }
}