package com.monish.androidlearning.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.monish.androidlearning.ui.home.HomeScreen
import com.monish.androidlearning.ui.lesson.LessonScreen
import com.monish.androidlearning.ui.theme.AndroidLearningTheme

private const val HomeRoute = "home"
private const val LessonRoute = "lesson/{lessonId}"

private fun lessonRoute(lessonId: String) = "lesson/$lessonId"

@Composable
fun AndroidLearningApp() {
    var darkTheme by rememberSaveable { mutableStateOf(false) }
    val navController = rememberNavController()

    AndroidLearningTheme(darkTheme = darkTheme) {
        NavHost(
            navController = navController,
            startDestination = HomeRoute,
        ) {
            composable(HomeRoute) {
                HomeScreen(
                    onOpenLesson = { lessonId ->
                        navController.navigate(lessonRoute(lessonId))
                    },
                )
            }
            composable(
                route = LessonRoute,
                arguments = listOf(navArgument("lessonId") { type = NavType.StringType }),
            ) { entry ->
                LessonScreen(
                    lessonId = entry.arguments?.getString("lessonId").orEmpty(),
                    darkTheme = darkTheme,
                    onDarkThemeChange = { darkTheme = it },
                    onBack = { navController.popBackStack() },
                )
            }
        }
    }
}
