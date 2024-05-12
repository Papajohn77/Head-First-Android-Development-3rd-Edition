package com.example.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tasks.databinding.TaskItemBinding

class TaskItemAdapter(private val clickListener: (taskId: Long) -> Unit) :
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
        holder.bind(item, clickListener)
    }

    class TaskItemViewHolder(private val binding: TaskItemBinding) :
        RecyclerView.ViewHolder(binding.root)
    {
        fun bind(item: Task, clickListener: (taskId: Long) -> Unit) {
            binding.task = item
            binding.root.setOnClickListener { clickListener(item.id) }
        }

        companion object {
            fun inflateFrom(parent: ViewGroup): TaskItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TaskItemBinding.inflate(layoutInflater, parent, false)
                return TaskItemViewHolder(binding)
            }
        }
    }
}