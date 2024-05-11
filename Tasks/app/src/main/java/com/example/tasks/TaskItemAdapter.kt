package com.example.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class TaskItemAdapter :
    ListAdapter<Task, TaskItemAdapter.TaskItemViewHolder>(TaskDiffItemCallback())
{
    override fun onCreateViewHolder(
        parent: ViewGroup, // parent refers to the Recycler View
        viewType: Int
    ): TaskItemViewHolder = TaskItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(
        holder: TaskItemViewHolder,
        position: Int // The item's position in the data
    ) {
        val item = getItem(position)
        holder.bind(item)
    }

    class TaskItemViewHolder(private val rootView: CardView) : RecyclerView.ViewHolder(rootView) {
        private val taskName = rootView.findViewById<TextView>(R.id.task_name)
        private val taskDone = rootView.findViewById<CheckBox>(R.id.task_done)

        fun bind(item: Task) {
            taskName.text = item.name
            taskDone.isChecked = item.isDone
        }

        companion object {
            fun inflateFrom(parent: ViewGroup): TaskItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.task_item, parent, false) as CardView
                return TaskItemViewHolder(view)
            }
        }
    }
}