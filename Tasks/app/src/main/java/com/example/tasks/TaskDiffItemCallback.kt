package com.example.tasks

import androidx.recyclerview.widget.DiffUtil

class TaskDiffItemCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task) = (oldItem.id == newItem.id)

    override fun areContentsTheSame(oldItem: Task, newItem: Task) = (oldItem == newItem)
}