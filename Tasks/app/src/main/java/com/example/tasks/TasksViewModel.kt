package com.example.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TasksViewModel(private val dao: TaskDAO) : ViewModel() {
    var taskName = ""

    private val tasks = dao.getAll()
    val tasksString = tasks.map { formatTasks(it) }

    fun addTask() {
        viewModelScope.launch {
            dao.insert(
                Task(name = taskName)
            )
        }
    }

    private fun formatTasks(tasks: List<Task>): String {
        return tasks.fold("") {
            formattedTasks, task -> formattedTasks + '\n' + formatTask(task)
        }
    }

    private fun formatTask(task: Task): String {
        var formattedTask = "ID: ${task.id}"
        formattedTask += '\n' + "Name: ${task.name}"
        formattedTask += '\n' + "Complete: ${task.isDone}"
        formattedTask += '\n'
        return formattedTask
    }
}