package com.example.todolist

import com.example.todolist.model.ToDoItem

interface OnItemClick {
    fun itemClicked(item: ToDoItem)
}