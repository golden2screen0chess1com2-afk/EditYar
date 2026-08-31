package com.editro.dailyplanner.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.editro.dailyplanner.ui.components.TaskItem
import com.editro.dailyplanner.viewmodel.PlannerViewModel

@Composable
fun CompletedScreen(viewModel: PlannerViewModel = viewModel()) {
    val completed by viewModel.completedTasks.collectAsState(initial = emptyList())

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("انجام‌شده‌ها", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(12.dp))

        if (completed.isEmpty()) {
            Text("هنوز کاری تکمیل نکرده‌ای.")
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(completed, key = { it.id }) { task ->
                    TaskItem(
                        task = task,
                        isOverlapping = false,
                        onToggleCompleted = { viewModel.unmarkCompleted(task) },
                        onEdit = { },
                        onDelete = { viewModel.deleteTask(task) }
                    )
                }
            }
        }
    }
}
