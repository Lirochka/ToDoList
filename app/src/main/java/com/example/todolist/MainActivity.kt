package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.todolist.room.AppDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var stubContainer: LinearLayout
    private lateinit var fab: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CustomAdapter
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MyLog", "onCreated been started")

        recyclerView = findViewById(R.id.main_recyclerView)
        stubContainer = findViewById(R.id.main_no_items_container)
        fab = findViewById(R.id.main_fab)

        fab.setOnClickListener {
            val dialog = CustomDialog(this)
            dialog.show()
        }

        recyclerView.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<ToDoItem>()

//        for (item in 1..20) {
//            data.add(ToDoItem("title", "description", item))
//        }
//
        if (data.isEmpty()) {
            Log.d("MyLog", "List is Empty")

            stubContainer.visibility = VISIBLE
            recyclerView.visibility = INVISIBLE
        } else {
            Log.d("MyLog", "List is NOT Empty")

            stubContainer.visibility = INVISIBLE
            recyclerView.visibility = VISIBLE
        }

        adapter = CustomAdapter(data)
        recyclerView.adapter = adapter
        Log.d("MyLog", "onCreated been finished")

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()
    }

    fun addItem(item: ToDoItem) {
        stubContainer.visibility = INVISIBLE
        recyclerView.visibility = VISIBLE
        //adapter.addItem(item)
        //вызов db
        db.toDoDao().insertItem(item)
    }
}