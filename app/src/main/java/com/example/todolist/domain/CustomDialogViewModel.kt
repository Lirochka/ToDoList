package com.example.todolist.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.PrefsRepository
import com.example.todolist.model.ToDoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CustomDialogViewModel @Inject constructor(
    private val prefsRepository: PrefsRepository,
) : ViewModel() {

    private var todoItem: MutableLiveData<ToDoItem> = MutableLiveData()
    val   toDoItemResult: LiveData<ToDoItem> = todoItem

    /**
     * Provides preferences value for ToDo item
     */
    fun getToDoItemFromPrefs() {
        val result = prefsRepository.getTodoItem()
        todoItem.postValue(result)
    }

    /**
     * Save data in shared preferences manager
     * @param key - provide prefs information to save data
     * @param value - provide data that need to be saved in prefs
     */
    fun saveDataInPrefs(key: String, value: String) {
        prefsRepository.saveDataInPrefs(key, value)
    }
}