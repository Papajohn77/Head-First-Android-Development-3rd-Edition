package com.example.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class EditTaskViewModelFactory(private val taskId: Long, private val dao: TaskDAO) :
    ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (!modelClass.isAssignableFrom(EditTaskViewModel::class.java)) {
            throw IllegalArgumentException("Unknown ViewModel")
        }
        return EditTaskViewModel(taskId, dao) as T
    }
}