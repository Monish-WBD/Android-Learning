package com.monish.androidlearning.model

data class Lesson(
    val id: String,
    val number: String,
    val title: String,
    val summary: String,
    val explanation: String,
)

val lessons: List<Lesson> = listOf(
    Lesson(
        id = "composables",
        number = "01",
        title = "Composables",
        summary = "UI is a function of the data you pass in.",
        explanation = "A composable function describes a piece of the screen. Compose calls it again whenever the values it reads change, so the UI stays in step with your data.",
    ),
    Lesson(
        id = "state",
        number = "02",
        title = "State",
        summary = "Remember a value, and the screen updates with it.",
        explanation = "State is data that can change. remember keeps it across recompositions. When you update that value, every composable that read it runs again.",
    ),
    Lesson(
        id = "layout",
        number = "03",
        title = "Layout",
        summary = "Column, Row, and Box place the same children differently.",
        explanation = "Compose lays out children with other composables. A Column stacks them vertically, a Row places them side by side, and a Box layers them.",
    ),
    Lesson(
        id = "material",
        number = "04",
        title = "Material 3",
        summary = "Buttons, cards, and color come from one theme.",
        explanation = "Material 3 components read color, type, and shape from the theme. Switching the theme updates every component that uses those tokens.",
    ),
)

fun lessonById(id: String): Lesson? = lessons.firstOrNull { it.id == id }
