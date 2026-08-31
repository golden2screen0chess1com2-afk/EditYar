package com.editro.dailyplanner.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.editro.dailyplanner.viewmodel.PlannerViewModel

@Composable
fun IdeasScreen(viewModel: PlannerViewModel = viewModel()) {
    val ideas by viewModel.ideas.collectAsState(initial = emptyList())
    var newIdeaText by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("ایده‌ها / بعداً", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(12.dp))

        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            OutlinedTextField(
                value = newIdeaText,
                onValueChange = { newIdeaText = it },
                placeholder = { Text("ایده یا کار غیرضروری‌ات را اینجا بنویس…") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                if (newIdeaText.isNotBlank()) {
                    viewModel.addIdea(newIdeaText.trim())
                    newIdeaText = ""
                }
            }) { Text("ثبت") }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(ideas, key = { it.id }) { idea ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    Text(idea.text, modifier = Modifier.weight(1f))
                    IconButton(onClick = { viewModel.deleteIdea(idea) }) {
                        Icon(Icons.Filled.Delete, contentDescription = "حذف")
                    }
                }
            }
        }
    }
}
