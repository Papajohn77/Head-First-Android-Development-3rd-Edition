package com.example.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TasksViewModelFactory(private val dao: TaskDAO) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (!modelClass.isAssignableFrom(TasksViewModel::class.java)) {
            throw IllegalArgumentException("Unknown ViewModel")
        }
        return TasksViewModel(dao) as T
    }
}