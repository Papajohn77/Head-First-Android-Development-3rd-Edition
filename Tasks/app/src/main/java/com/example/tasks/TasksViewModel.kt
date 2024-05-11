package com.example.tasks

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TasksViewModel(private val dao: TaskDAO) : ViewModel() {
    var taskName = MutableLiveData<String>()

    val tasks = dao.getAll()

    fun addTask() {
        viewModelScope.launch {
            if (taskName.value != "") {
                dao.insert(
                    Task(name = taskName.value!!)
                )
            }
            taskName.value = ""
        }
    }
}