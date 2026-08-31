package com.editro.dailyplanner.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface IdeaDao {
    @Query("SELECT * FROM ideas ORDER BY createdAt DESC")
    fun getAllIdeas(): Flow<List<Idea>>

    @Insert
    suspend fun insert(idea: Idea): Long

    @Delete
    suspend fun delete(idea: Idea)
}

@Dao
interface ProjectDao {
    @Query("SELECT * FROM projects ORDER BY createdAt DESC")
    fun getAllProjects(): Flow<List<Project>>

    @Insert
    suspend fun insert(project: Project): Long

    @Delete
    suspend fun delete(project: Project)
}
