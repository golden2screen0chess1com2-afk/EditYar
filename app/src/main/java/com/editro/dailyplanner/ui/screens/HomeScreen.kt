package com.editro.dailyplanner.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.editro.dailyplanner.data.Task
import com.editro.dailyplanner.ui.components.TaskEditorDialog
import com.editro.dailyplanner.ui.components.TaskItem
import com.editro.dailyplanner.util.findOverlappingIds
import com.editro.dailyplanner.viewmodel.PlannerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: PlannerViewModel = viewModel(),
    onOpenMenu: () -> Unit
) {
    val tasks by viewModel.activeTasks.collectAsState(initial = emptyList())
    val overlappingIds = remember(tasks) { tasks.findOverlappingIds() }

    var showAddDialog by remember { mutableStateOf(false) }
    var editingTask by remember { mutableStateOf<Task?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("ادیت‌یار", style = MaterialTheme.typography.titleMedium)
                        Text(
                            "برنامه‌ریزی روزانه",
                            style = MaterialTheme.typography.bodyMedium,
                            color = com.editro.dailyplanner.ui.theme.TextGray
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onOpenMenu) {
                        Icon(Icons.Filled.Menu, contentDescription = "منو")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Filled.Add, contentDescription = "افزودن کار")
            }
        }
    ) { padding ->
        if (tasks.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("هنوز کاری اضافه نکرده‌ای. با دکمه + شروع کن.")
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(tasks, key = { it.id }) { task ->
                    TaskItem(
                        task = task,
                        isOverlapping = overlappingIds.contains(task.id),
                        onToggleCompleted = {
                            if (task.isCompleted) viewModel.unmarkCompleted(task)
                            else viewModel.markCompleted(task)
                        },
                        onEdit = { editingTask = task },
                        onDelete = { viewModel.deleteTask(task) }
                    )
                }
            }
        }
    }

    if (showAddDialog) {
        TaskEditorDialog(
            onDismiss = { showAddDialog = false },
            onConfirm = { title, start, duration ->
                viewModel.addTask(title, start, start + duration)
                showAddDialog = false
            }
        )
    }

    editingTask?.let { task ->
        TaskEditorDialog(
            existing = task,
            onDismiss = { editingTask = null },
            onConfirm = { title, start, duration ->
                viewModel.updateTask(task.copy(title = title, startMinute = start, endMinute = start + duration))
                editingTask = null
            }
        )
    }
}
