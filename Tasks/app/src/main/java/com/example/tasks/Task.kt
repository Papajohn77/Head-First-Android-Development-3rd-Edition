package com.example.tasks

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    // ColumnInfo(name = "name")
    var name: String = "",
    // ColumnInfo(name = "isDone")
    var isDone: Boolean = false,
)