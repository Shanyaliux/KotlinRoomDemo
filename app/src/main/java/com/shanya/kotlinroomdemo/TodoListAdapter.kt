package com.shanya.kotlinroomdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoListAdapter internal constructor(context: Context): RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var todos = emptyList<Todo>() // Cached copy of todo


    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val todoItemView: TextView = itemView.findViewById(R.id.textView)
        val todoContentView: TextView = itemView.findViewById(R.id.textView1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item,parent,false)
        return TodoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val current = todos[position]
        holder.todoItemView.text = current.title
        holder.todoContentView.text = current.content
    }

    internal fun setTodo(todos: List<Todo>){
        this.todos = todos
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}