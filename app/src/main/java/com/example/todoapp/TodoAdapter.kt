package com.example.todoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class TodoAdapter(val list: List<TodoModel>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_todo, parent, false)
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemId(position: Int): Long {
        return list[position].id
    }

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // Initialize the views as lateinit properties
        // Assign the views using findViewById in the initializer block
        private var viewColorTag: View = itemView.findViewById(R.id.viewColorTag)
        private var txtShowTitle: TextView = itemView.findViewById(R.id.txtShowTitle)
        private var txtShowTask: TextView = itemView.findViewById(R.id.txtShowTask)
        private var txtShowCategory: TextView = itemView.findViewById(R.id.txtShowCategory)
        private var txtShowTime: TextView = itemView.findViewById(R.id.txtShowTime)
        private var txtShowDate: TextView = itemView.findViewById(R.id.txtShowDate)

        fun bind(todoModel: TodoModel) {
            with(itemView) {
                val colors = resources.getIntArray(R.array.random_color)
                val randomColor = colors[Random().nextInt(colors.size)]
                viewColorTag.setBackgroundColor(randomColor)
                txtShowTitle.text = todoModel.title
                txtShowTask.text = todoModel.description
                txtShowCategory.text = todoModel.category
                updateTime(todoModel.time)
                updateDate(todoModel.date)
            }
        }

        private fun updateTime(time: Long) {
            val myFormat = "h:mm a"
            val sdf = SimpleDateFormat(myFormat)
            txtShowTime.text = sdf.format(Date(time))
        }

        private fun updateDate(time: Long) {
            val myFormat = "EEE, d MMM yyyy"
            val sdf = SimpleDateFormat(myFormat)
            txtShowDate.text = sdf.format(Date(time))
        }
    }
}
