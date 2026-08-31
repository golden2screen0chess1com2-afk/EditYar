package com.editro.dailyplanner.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.editro.dailyplanner.data.*
import kotlinx.coroutines.launch

class PlannerViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = TaskRepository(AppDatabase.getInstance(application))

    val activeTasks = repo.activeTasks
    val completedTasks = repo.completedTasks
    val ideas = repo.ideas
    val projects = repo.projects

    fun addTask(title: String, startMinute: Int, endMinute: Int, isBreak: Boolean = false, projectId: Long? = null) {
        viewModelScope.launch {
            repo.addTask(Task(title = title, startMinute = startMinute, endMinute = endMinute, isBreak = isBreak, projectId = projectId))
        }
    }

    fun updateTask(task: Task) = viewModelScope.launch { repo.updateTask(task) }

    fun deleteTask(task: Task) = viewModelScope.launch { repo.deleteTask(task) }

    // علامت زدن به‌عنوان "انجام شد"
    fun markCompleted(task: Task) = viewModelScope.launch {
        repo.updateTask(task.copy(isCompleted = true))
    }

    fun unmarkCompleted(task: Task) = viewModelScope.launch {
        repo.updateTask(task.copy(isCompleted = false))
    }

    // "تنظیم مجدد": فقط کارهایی که هنوز شروع نشده‌اند را می‌توان جابه‌جا کرد.
    // کارهای انجام‌شده دست نمی‌خورند.
    fun addIdea(text: String) = viewModelScope.launch { repo.addIdea(Idea(text = text)) }
    fun deleteIdea(idea: Idea) = viewModelScope.launch { repo.deleteIdea(idea) }

    fun addProject(name: String) = viewModelScope.launch { repo.addProject(Project(name = name)) }
    fun deleteProject(project: Project) = viewModelScope.launch { repo.deleteProject(project) }
}
