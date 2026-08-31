package com.editro.dailyplanner.data

class TaskRepository(private val db: AppDatabase) {
    val activeTasks = db.taskDao().getActiveTasks()
    val completedTasks = db.taskDao().getCompletedTasks()
    val ideas = db.ideaDao().getAllIdeas()
    val projects = db.projectDao().getAllProjects()

    suspend fun addTask(task: Task) = db.taskDao().insert(task)
    suspend fun updateTask(task: Task) = db.taskDao().update(task)
    suspend fun deleteTask(task: Task) = db.taskDao().delete(task)

    suspend fun addIdea(idea: Idea) = db.ideaDao().insert(idea)
    suspend fun deleteIdea(idea: Idea) = db.ideaDao().delete(idea)

    suspend fun addProject(project: Project) = db.projectDao().insert(project)
    suspend fun deleteProject(project: Project) = db.projectDao().delete(project)

    fun tasksForProject(projectId: Long) = db.taskDao().getTasksForProject(projectId)
}
