package com.editro.dailyplanner.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * هر ردیف این جدول یعنی یک "کار" که کاربر برای روزش تعریف کرده.
 * startMinute و endMinute بر حسب دقیقه از نیمه‌شب حساب می‌شوند (مثلاً 07:30 = 450).
 * این کار محاسبه تداخل و مرتب‌سازی را خیلی ساده می‌کند.
 */
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
