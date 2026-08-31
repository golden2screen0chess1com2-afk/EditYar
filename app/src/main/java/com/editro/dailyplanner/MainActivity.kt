package com.editro.dailyplanner

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.editro.dailyplanner.ui.components.HamburgerMenuContent
import com.editro.dailyplanner.ui.screens.*
import com.editro.dailyplanner.ui.theme.DailyPlannerTheme
import kotlinx.coroutines.launch

private const val PREFS_NAME = "edityar_prefs"
private const val KEY_FIRST_LAUNCH_DONE = "first_launch_done"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyPlannerTheme {
                AppRoot()
            }
        }
    }
}

private fun isFirstLaunch(context: Context): Boolean {
    val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    return !prefs.getBoolean(KEY_FIRST_LAUNCH_DONE, false)
}

private fun markFirstLaunchDone(context: Context) {
    context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        .edit().putBoolean(KEY_FIRST_LAUNCH_DONE, true).apply()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppRoot() {
    val context = LocalContext.current
    val navController: NavHostController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: "splash"

    val startDestination = if (isFirstLaunch(context)) "splash" else "home"

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = currentRoute != "splash",
        drawerContent = {
            HamburgerMenuContent(
                currentRoute = currentRoute,
                onNavigate = { route ->
                    scope.launch { drawerState.close() }
                    if (route != currentRoute) {
                        navController.navigate(route) {
                            popUpTo("home")
                            launchSingleTop = true
                        }
                    }
                }
            )
        }
    ) {
        NavHost(navController = navController, startDestination = startDestination) {
            composable("splash") {
                SplashScreen(onStart = {
                    markFirstLaunchDone(context)
                    navController.navigate("home") {
                        popUpTo("splash") { inclusive = true }
                    }
                })
            }
            composable("home") {
                HomeScreen(onOpenMenu = { scope.launch { drawerState.open() } })
            }
            composable("ideas") { IdeasScreen() }
            composable("projects") { ProjectsScreen() }
            composable("completed") { CompletedScreen() }
            composable("settings") { SettingsScreen() }
            composable("about") { AboutScreen() }
        }
    }
}
