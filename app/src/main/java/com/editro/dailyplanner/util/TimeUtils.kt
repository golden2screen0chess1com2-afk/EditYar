package com.editro.dailyplanner.util

import com.editro.dailyplanner.data.Task

object TimeUtils {

    // تبدیل دقیقه (مثلاً 450) به نمایش ساعت "07:30"
    fun minutesToLabel(totalMinutes: Int): String {
        val m = ((totalMinutes % 1440) + 1440) % 1440
        val h = m / 60
        val min = m % 60
        return "%02d:%02d".format(h, min)
    }

    fun hhmmToMinutes(hour: Int, minute: Int): Int = hour * 60 + minute
}

/**
 * منطق تداخل زمانی:
 * دو کار وقتی تداخل دارند که بازه زمانی‌شان با هم همپوشانی داشته باشد.
 * این تابع فقط تشخیص می‌دهد و هیچ‌چیزی را تغییر یا حذف نمی‌کند —
 * طبق قانون پروژه، تصمیم نهایی همیشه با کاربر است.
 */
fun List<Task>.findOverlappingIds(): Set<Long> {
    val overlapping = mutableSetOf<Long>()
    val sorted = this.sortedBy { it.startMinute }
    for (i in sorted.indices) {
        for (j in i + 1 until sorted.size) {
            val a = sorted[i]
            val b = sorted[j]
            if (b.startMinute >= a.endMinute) break // چون مرتب است، دیگر نیازی به ادامه نیست
            if (a.startMinute < b.endMinute && b.startMinute < a.endMinute) {
                overlapping.add(a.id)
                overlapping.add(b.id)
            }
        }
    }
    return overlapping
}
