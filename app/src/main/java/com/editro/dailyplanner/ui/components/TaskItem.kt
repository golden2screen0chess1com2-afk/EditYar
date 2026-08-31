package com.editro.dailyplanner.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.editro.dailyplanner.data.Task
import com.editro.dailyplanner.ui.theme.*
import com.editro.dailyplanner.util.TimeUtils

/**
 * ردیف یک کار داخل جدول — طراحی شیشه‌ای با یک نقطه/خط طلایی سمت کنار
 * که حس یک Timeline زیبا می‌دهد، نه یک لیست خشک.
 */
@Composable
fun TaskItem(
    task: Task,
    isOverlapping: Boolean,
    onToggleCompleted: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    val strikeProgress by animateFloatAsState(
        targetValue = if (task.isCompleted) 1f else 0f,
        animationSpec = tween(durationMillis = 220),
        label = "strike"
    )

    Row(modifier = Modifier.fillMaxWidth()) {
        // نقطه و خط طلایی سمت راست — جلوه Timeline
        Column(
            modifier = Modifier.width(20.dp).fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(if (task.isCompleted) 10.dp else 12.dp)
                    .clip(CircleShape)
                    .background(if (task.isBreak) TextGray else Gold)
            )
        }

        GlassCard(
            modifier = Modifier.weight(1f),
            cornerRadius = 16
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                IconButton(onClick = onToggleCompleted) {
                    Icon(
                        imageVector = if (task.isCompleted) Icons.Filled.CheckCircle else Icons.Filled.RadioButtonUnchecked,
                        contentDescription = null,
                        tint = Gold
                    )
                }

                Column(modifier = Modifier.weight(1f).padding(horizontal = 4.dp)) {
                    Text(
                        text = "${TimeUtils.minutesToLabel(task.startMinute)} – ${TimeUtils.minutesToLabel(task.endMinute)}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = GoldDeep
                    )
                    Text(
                        text = task.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = TextDark,
                        textDecoration = if (strikeProgress > 0.5f) TextDecoration.LineThrough else null
                    )
                    if (isOverlapping && !task.isCompleted) {
                        Text(
                            text = "این کار با کار دیگری هم‌زمان است",
                            style = MaterialTheme.typography.bodyMedium,
                            color = AmberNotice
                        )
                    }
                }

                IconButton(onClick = onEdit) {
                    Icon(Icons.Filled.Edit, contentDescription = "ویرایش", tint = TextGray)
                }
                IconButton(onClick = onDelete) {
                    Icon(Icons.Filled.Delete, contentDescription = "حذف", tint = TextGray)
                }
            }
        }
    }
}
