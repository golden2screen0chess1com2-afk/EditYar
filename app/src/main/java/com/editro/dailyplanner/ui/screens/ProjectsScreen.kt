package com.editro.dailyplanner.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.editro.dailyplanner.viewmodel.PlannerViewModel

@Composable
fun ProjectsScreen(viewModel: PlannerViewModel = viewModel()) {
    val projects by viewModel.projects.collectAsState(initial = emptyList())
    var newProjectName by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("پروژه‌ها", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(12.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = newProjectName,
                onValueChange = { newProjectName = it },
                placeholder = { Text("نام پروژه (مثلاً ادیت ویدیوی تبلیغاتی)") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                if (newProjectName.isNotBlank()) {
                    viewModel.addProject(newProjectName.trim())
                    newProjectName = ""
                }
            }) { Text("افزودن") }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(projects, key = { it.id }) { project ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(project.name, modifier = Modifier.weight(1f))
                    IconButton(onClick = { viewModel.deleteProject(project) }) {
                        Icon(Icons.Filled.Delete, contentDescription = "حذف")
                    }
                }
            }
        }
    }
}
