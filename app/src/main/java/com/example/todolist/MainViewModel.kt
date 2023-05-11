package com.example.todolist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todolist.data.RoomManagerImpl

class MainViewModel(app: Application): AndroidViewModel(app) {

    private val roomManager: RoomManager = RoomManagerImpl(app)

    private var todoItemList: MutableLiveData<List<ToDoItem>> = MutableLiveData()
    val toDoItemListResult: LiveData<List<ToDoItem>> = todoItemList

    fun getAllData() {
       val result = roomManager.getAll()
        todoItemList.postValue(result)
    }

    fun insertItem(item: ToDoItem) {
        todoItemList.value.let {
            todoItemList.postValue(it?.plus(item))
            roomManager.insertItem(item)
        }
    }

    fun updateItem(item: ToDoItem) {
        roomManager.updateItem(item)
    }

    fun deleteItem(item: ToDoItem) {
        todoItemList.value.let {
            todoItemList.postValue(it?.minus(item))
            roomManager.deleteItem(item)
        }
    }
}