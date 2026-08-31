package com.editro.dailyplanner.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.editro.dailyplanner.ui.theme.GlassBorder
import com.editro.dailyplanner.ui.theme.GlassFill

/**
 * کارت شیشه‌ای سبک: نیمه‌شفاف، بردر ظریف طلایی، سایه نرم، گوشه گرد.
 * از Blur سنگین استفاده نشده تا اپ سبک و روان بماند.
 */
@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    cornerRadius: Int = 20,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .shadow(elevation = 6.dp, shape = RoundedCornerShape(cornerRadius.dp), clip = false)
            .background(GlassFill, RoundedCornerShape(cornerRadius.dp))
            .border(1.dp, GlassBorder, RoundedCornerShape(cornerRadius.dp))
            .padding(16.dp)
    ) {
        content()
    }
}
