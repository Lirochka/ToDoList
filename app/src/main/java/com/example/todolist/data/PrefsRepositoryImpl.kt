package com.example.todolist.data

import android.content.SharedPreferences
import com.example.todolist.PrefsRepository
import com.example.todolist.model.ToDoItem
import javax.inject.Inject

/**
 * Manage that handles logic with shared preferences
 */
class PrefsRepositoryImpl @Inject constructor(

    private val sharedPreferences: SharedPreferences
    ): PrefsRepository {
    override fun getTodoItem(): ToDoItem {
        val title =
            sharedPreferences.getString(PREFS_TITLE_KEY, PREFS_DEFAULT_VALUE) ?: PREFS_DEFAULT_VALUE
        val description =
            sharedPreferences.getString(PREFS_DESCRIPTION_KEY, PREFS_DEFAULT_VALUE) ?: PREFS_DEFAULT_VALUE
        val number =
            sharedPreferences.getString(PREFS_NUMBER_KEY, PREFS_DEFAULT_VALUE) ?: PREFS_DEFAULT_VALUE
      return ToDoItem(0, title, description, number)
    }
    override fun saveDataInPrefs(key: String, value : String) {
        with(sharedPreferences.edit()) {
            putString(key, value)
            apply()
        }
    }
    companion object {
        const val PREFS_TITLE_KEY = "titleKey"
        const val PREFS_DESCRIPTION_KEY = "descriptionKey"
        const val PREFS_NUMBER_KEY = "numberKey"
        const val PREFS_NAME = "preferences"
        const val PREFS_DEFAULT_VALUE = ""
    }
}