package com.example.todolist.domain

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todolist.PrefsManager
import com.example.todolist.model.ToDoItem
import com.example.todolist.data.PrefsManagerImpl

class CustomDialogViewModel(app: Application): AndroidViewModel(app) {
    private val prefsManager: PrefsManager = PrefsManagerImpl(app)

    private var todoItem: MutableLiveData<ToDoItem> = MutableLiveData()
    val toDoItemResult: LiveData<ToDoItem> = todoItem

    /**
     * Provides preferences value for ToDo item
     */
    fun getToDoItemFromPrefs() {
       val result = prefsManager.getTodoItem()
        todoItem.postValue(result)
    }

    /**
     * Save data in shared preferences manager
     * @param key - provide prefs information to save data
     * @param value - provide data that need to be saved in prefs
     */
    fun saveDataInPrefs(key: String, value: String) {
        prefsManager.saveDataInPrefs(key, value)
    }
}