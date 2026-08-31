package com.editro.dailyplanner.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen() {
    var breakEnabled by remember { mutableStateOf(true) }
    var breakDuration by remember { mutableStateOf(15) }
    var is24HourFormat by remember { mutableStateOf(true) }
    var notificationsEnabled by remember { mutableStateOf(true) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("تنظیمات", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(20.dp))

        SettingRow("اعلان‌ها") {
            Switch(checked = notificationsEnabled, onCheckedChange = { notificationsEnabled = it })
        }

        SettingRow("استراحت فعال باشد") {
            Switch(checked = breakEnabled, onCheckedChange = { breakEnabled = it })
        }

        if (breakEnabled) {
            Text("مدت استراحت پیش‌فرض: $breakDuration دقیقه")
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf(15, 30, 45, 60).forEach { minutes ->
                    FilterChip(
                        selected = breakDuration == minutes,
                        onClick = { breakDuration = minutes },
                        label = { Text("$minutes") }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        SettingRow("فرمت ۲۴ ساعته") {
            Switch(checked = is24HourFormat, onCheckedChange = { is24HourFormat = it })
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text("مدیریت اطلاعات", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedButton(onClick = { /* TODO: Backup در نسخه بعد */ }) { Text("پشتیبان‌گیری (Backup)") }
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedButton(onClick = { /* TODO: Restore در نسخه بعد */ }) { Text("بازیابی (Restore)") }
    }
}

@Composable
private fun SettingRow(label: String, control: @Composable () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label)
        control()
    }
}
