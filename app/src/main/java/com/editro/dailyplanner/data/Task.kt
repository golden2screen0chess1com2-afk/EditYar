package com.editro.dailyplanner.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val startMinute: Int,
    val endMinute: Int,
    val isCompleted: Boolean = false,
    val isBreak: Boolean = false,
    val projectId: Long? = null
)
