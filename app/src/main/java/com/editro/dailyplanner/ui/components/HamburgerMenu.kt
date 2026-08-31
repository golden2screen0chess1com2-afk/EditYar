package com.editro.dailyplanner.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class MenuItem(val label: String, val route: String, val emoji: String)

val menuItems = listOf(
    MenuItem("خانه", "home", "🏠"),
    MenuItem("ایده‌ها / بعداً", "ideas", "💡"),
    MenuItem("پروژه‌ها", "projects", "🎬"),
    MenuItem("انجام‌شده‌ها", "completed", "✅"),
    MenuItem("تنظیمات", "settings", "⚙️"),
    MenuItem("درباره برنامه", "about", "👨‍💻")
)

@Composable
fun HamburgerMenuContent(
    currentRoute: String,
    onNavigate: (String) -> Unit
) {
    ModalDrawerSheet {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "ادیت‌یار",
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
            style = MaterialTheme.typography.titleLarge
        )
        HorizontalDivider()
        menuItems.forEach { item ->
            NavigationDrawerItem(
                label = { Text("${item.emoji}  ${item.label}") },
                selected = currentRoute == item.route,
                onClick = { onNavigate(item.route) },
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
            )
        }
    }
}
