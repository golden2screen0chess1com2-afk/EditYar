package com.editro.dailyplanner.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.editro.dailyplanner.ui.components.GlassCard
import com.editro.dailyplanner.ui.theme.Gold
import com.editro.dailyplanner.ui.theme.TextGray

@Composable
fun SplashScreen(onStart: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("ادیت‌یار", style = MaterialTheme.typography.titleLarge, color = Gold)
        Spacer(modifier = Modifier.height(4.dp))
        Text("برنامه‌ریزی روزانه", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text("روزت را ساده‌تر بچین.", style = MaterialTheme.typography.bodyMedium, color = TextGray)

        Spacer(modifier = Modifier.height(32.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            ConceptGlassCard("⏰", "زمان")
            ConceptGlassCard("📝", "کار")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            ConceptGlassCard("⏸️", "استراحت")
            ConceptGlassCard("✅", "انجام‌شده")
        }

        Spacer(modifier = Modifier.height(40.dp))

        Button(onClick = onStart, modifier = Modifier.fillMaxWidth(0.8f)) {
            Text("شروع برنامه‌ریزی")
        }
    }
}

@Composable
private fun ConceptGlassCard(emoji: String, label: String) {
    GlassCard(modifier = Modifier.size(90.dp), cornerRadius = 18) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(emoji, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(label, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
        }
    }
}
