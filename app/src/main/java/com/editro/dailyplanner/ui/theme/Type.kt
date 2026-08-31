package com.editro.dailyplanner.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.editro.dailyplanner.R

// نکته: فایل ExtraBold به‌صورت woff2 بود و اندروید آن را پشتیبانی نمی‌کند،
// به همین دلیل برای وزن Bold از فونت Black استفاده شده است.
val VazirmatnFamily = FontFamily(
    Font(R.font.vazirmatn_regular, FontWeight.Normal),
    Font(R.font.vazirmatn_medium, FontWeight.Medium),
    Font(R.font.vazirmatn_black, FontWeight.Bold),
    Font(R.font.vazirmatn_black, FontWeight.ExtraBold)
)

val AppTypography = Typography(
    bodyLarge = TextStyle(fontFamily = VazirmatnFamily, fontWeight = FontWeight.Normal, fontSize = 16.sp),
    bodyMedium = TextStyle(fontFamily = VazirmatnFamily, fontWeight = FontWeight.Normal, fontSize = 14.sp),
    titleLarge = TextStyle(fontFamily = VazirmatnFamily, fontWeight = FontWeight.ExtraBold, fontSize = 24.sp),
    titleMedium = TextStyle(fontFamily = VazirmatnFamily, fontWeight = FontWeight.Medium, fontSize = 18.sp),
    labelLarge = TextStyle(fontFamily = VazirmatnFamily, fontWeight = FontWeight.Medium, fontSize = 14.sp)
)
