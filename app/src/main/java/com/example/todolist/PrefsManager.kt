package com.example.todolist

import com.example.todolist.model.ToDoItem

interface PrefsManager {
    /**
     * Return todo item from prefs
     */
    fun getTodoItem() : ToDoItem

    /**
     * Saving data in prefs
     */
    fun saveDataInPrefs(key: String, value : String)
}