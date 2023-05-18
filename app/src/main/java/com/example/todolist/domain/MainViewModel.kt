package com.example.todolist.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.RoomRepository
import com.example.todolist.model.ToDoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val roomRepository: RoomRepository
): ViewModel() {

    private var todoItemList: MutableLiveData<List<ToDoItem>> = MutableLiveData()
    val toDoItemListResult: LiveData<List<ToDoItem>> = todoItemList

    /**
     * Provides all data from room
     */
    fun getAllData() {
       val result = roomRepository.getAll()
        todoItemList.postValue(result)
    }

    /**
     * Insert Item in room data base
     * @param item - provides item that need to be insert in room data base
     */
    fun insertItem(item: ToDoItem) {
        todoItemList.value.let {
            todoItemList.postValue(it?.plus(item))
            roomRepository.insertItem(item)
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
        roomRepository.updateItem(item)
    }

    /**
     * Delete existing Item from room data base
     * @param item - provides item that need to be deleted from room data base
     */
    fun deleteItem(item: ToDoItem) {
        todoItemList.value.let {
            todoItemList.postValue(it?.minus(item))
            roomRepository.deleteItem(item)
        }
    }
}