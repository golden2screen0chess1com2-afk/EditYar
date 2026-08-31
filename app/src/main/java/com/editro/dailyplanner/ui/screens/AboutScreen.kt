package com.editro.dailyplanner.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.editro.dailyplanner.ui.components.GlassCard
import com.editro.dailyplanner.ui.theme.Gold
import com.editro.dailyplanner.ui.theme.TextGray

@Composable
fun AboutScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("ادیت‌یار", style = MaterialTheme.typography.titleLarge, color = Gold)
        Text("برنامه‌ریزی روزانه", style = MaterialTheme.typography.bodyMedium, color = TextGray)

        Spacer(modifier = Modifier.height(24.dp))

        GlassCard {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("علی مسجدی", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text("ادیتور و تولید کننده محتوا", style = MaterialTheme.typography.bodyMedium, color = TextGray)
                Spacer(modifier = Modifier.height(4.dp))
                Text("سازنده این برنامه", style = MaterialTheme.typography.bodyMedium, color = TextGray)
            }
        }
    }
}
