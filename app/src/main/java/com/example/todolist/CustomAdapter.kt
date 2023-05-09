package com.example.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(
    private var mList: MutableList<ToDoItem>,
    private val click: OnItemClick,
) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return mList.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewHolder = mList[position]

        holder.title.text = itemsViewHolder.title
        holder.description.text = itemsViewHolder.description
        holder.number.text = itemsViewHolder.number.toString()
        holder.container.setOnClickListener {
            click.itemClicked(mList[position])
        }
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.item_recycler_title)
        val description: TextView = itemView.findViewById(R.id.item_recycler_description)
        val number: TextView = itemView.findViewById(R.id.item_recycler_number)
        val container: ConstraintLayout = itemView.findViewById(R.id.item_recycler_container)
    }

    fun updateList(updatedList: List<ToDoItem>) {
        mList = updatedList.toMutableList()
        notifyDataSetChanged()
    }
}