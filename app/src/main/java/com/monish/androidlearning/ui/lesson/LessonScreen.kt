package com.monish.androidlearning.ui.lesson

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.monish.androidlearning.model.lessonById
import com.monish.androidlearning.ui.theme.Amber
import com.monish.androidlearning.ui.theme.Blue
import com.monish.androidlearning.ui.theme.Teal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonScreen(
    lessonId: String,
    darkTheme: Boolean,
    onDarkThemeChange: (Boolean) -> Unit,
    onBack: () -> Unit,
) {
    val lesson = lessonById(lessonId)
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(lesson?.title ?: "Lesson") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                ),
            )
        },
    ) { innerPadding ->
        if (lesson == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(20.dp),
            ) {
                Text("That lesson is not in this app.")
            }
            return@Scaffold
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                text = lesson.explanation,
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                text = "Try it",
                style = MaterialTheme.typography.titleLarge,
            )
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                color = MaterialTheme.colorScheme.surfaceContainerLow,
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    when (lesson.id) {
                        "composables" -> ComposableDemo()
                        "state" -> StateDemo()
                        "layout" -> LayoutDemo()
                        "material" -> MaterialDemo(
                            darkTheme = darkTheme,
                            onDarkThemeChange = onDarkThemeChange,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ComposableDemo() {
    var name by rememberSaveable { mutableStateOf("Monish") }
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )
        Greeting(name = name.ifBlank { "there" })
        Text(
            text = "Greeting is a composable. It receives the name and returns this text.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun Greeting(name: String) {
    Text(
        text = "Hello, $name",
        style = MaterialTheme.typography.headlineMedium,
    )
}

@Composable
private fun StateDemo() {
    var count by rememberSaveable { mutableStateOf(0) }
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = "Count is $count",
            style = MaterialTheme.typography.headlineMedium,
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedButton(
                onClick = { count -= 1 },
                enabled = count > 0,
            ) {
                Text("Decrease")
            }
            Button(onClick = { count += 1 }) {
                Text("Increase")
            }
        }
        Text(
            text = "count is remembered here. Changing it recomposes this text.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

private enum class LayoutKind { Column, Row, Box }

@Composable
private fun LayoutDemo() {
    var kind by remember { mutableStateOf(LayoutKind.Column) }
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            LayoutKind.entries.forEach { option ->
                FilterChip(
                    selected = kind == option,
                    onClick = { kind = option },
                    label = { Text(option.name) },
                )
            }
        }
        when (kind) {
            LayoutKind.Column -> Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                LayoutBlocks()
            }
            LayoutKind.Row -> Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                LayoutBlocks()
            }
            LayoutKind.Box -> Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 28.dp),
            ) {
                DemoBlock("A", Blue, Modifier.align(Alignment.CenterStart))
                DemoBlock("B", Teal, Modifier.align(Alignment.Center))
                DemoBlock("C", Amber, Modifier.align(Alignment.CenterEnd))
            }
        }
    }
}

@Composable
private fun LayoutBlocks() {
    DemoBlock("A", Blue)
    DemoBlock("B", Teal)
    DemoBlock("C", Amber)
}

@Composable
private fun DemoBlock(
    label: String,
    color: Color,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        color = color,
    ) {
        Text(
            text = label,
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 22.dp, vertical = 14.dp),
        )
    }
}

@Composable
private fun MaterialDemo(
    darkTheme: Boolean,
    onDarkThemeChange: (Boolean) -> Unit,
) {
    var choice by rememberSaveable { mutableStateOf("Nothing selected yet") }
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("Dark theme", style = MaterialTheme.typography.titleMedium)
            Switch(
                checked = darkTheme,
                onCheckedChange = onDarkThemeChange,
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { choice = "Filled button" }) {
                Text("Filled")
            }
            OutlinedButton(onClick = { choice = "Outlined button" }) {
                Text("Outlined")
            }
        }
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text("Material card", style = MaterialTheme.typography.titleMedium)
                Text(
                    text = choice,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}
