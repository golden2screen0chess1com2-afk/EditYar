package com.editro.dailyplanner.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.editro.dailyplanner.data.Task
import com.editro.dailyplanner.util.TimeUtils
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskEditorDialog(
    existing: Task? = null,
    onDismiss: () -> Unit,
    onConfirm: (title: String, startMinute: Int, durationMinutes: Int) -> Unit
) {
    var title by remember { mutableStateOf(existing?.title ?: "") }

    val now = Calendar.getInstance()
    var startMinute by remember {
        mutableStateOf(existing?.startMinute ?: (now.get(Calendar.HOUR_OF_DAY) * 60 + now.get(Calendar.MINUTE)))
    }
    var durationMinutes by remember {
        mutableStateOf(existing?.let { it.endMinute - it.startMinute } ?: 60)
    }
    var durationText by remember { mutableStateOf(durationMinutes.toString()) }

    val hour = startMinute / 60
    val minute = startMinute % 60
    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (existing == null) "افزودن کار" else "ویرایش کار") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("نام کار") },
                    modifier = Modifier.fillMaxWidth()
                )

                Text("زمان شروع: ${TimeUtils.minutesToLabel(startMinute)}")
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedButton(onClick = {
                        android.app.TimePickerDialog(
                            context,
                            { _, h, m -> startMinute = TimeUtils.hhmmToMinutes(h, m) },
                            hour, minute, true
                        ).show()
                    }) {
                        Text("انتخاب ساعت")
                    }
                }

                OutlinedTextField(
                    value = durationText,
                    onValueChange = {
                        durationText = it
                        durationMinutes = it.toIntOrNull() ?: durationMinutes
                    },
                    label = { Text("مدت زمان (دقیقه)") },
                    modifier = Modifier.fillMaxWidth()
                )

                val endLabel = TimeUtils.minutesToLabel(startMinute + durationMinutes)
                Text("زمان پایان (خودکار): $endLabel")
            }
        },
        confirmButton = {
            TextButton(onClick = {
                if (title.isNotBlank() && durationMinutes > 0) {
                    onConfirm(title.trim(), startMinute, durationMinutes)
                }
            }) { Text("ذخیره") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("انصراف") }
        }
    )
}
