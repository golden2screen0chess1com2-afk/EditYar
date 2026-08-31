package com.editro.dailyplanner.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * تمام اطلاعات اپ روی خود گوشی (Local) ذخیره می‌شود.
 * هیچ اتصال اینترنتی یا سروری در کار نیست.
 */
@Database(entities = [Task::class, Idea::class, Project::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun ideaDao(): IdeaDao
    abstract fun projectDao(): ProjectDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "daily_planner.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
