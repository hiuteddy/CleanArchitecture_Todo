package com.fpl.hieunnph32561.myapplication.data.presentation.todo_detail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fpl.hieunnph32561.myapplication.domain.model.Todo

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoDetailScreen(navController: NavController, viewModel: TodoDetailViewModel = hiltViewModel(), todoId: Int? = null) {
    val todo by viewModel.todo.collectAsState()
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var completed by remember { mutableStateOf(false) }
    var dueDate by remember { mutableStateOf<Long?>(System.currentTimeMillis()) }
    var priority by remember { mutableStateOf("low") }

    LaunchedEffect(todoId) {
        todoId?.let { viewModel.loadTodo(it) }
    }

    LaunchedEffect(todo) {
        todo?.let {
            title = it.title
            description = it.description
            completed = it.completed
            dueDate = it.dueDate
            priority = it.priority
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (todoId == null) "Add Todo" else "Edit Todo") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.addOrUpdateTodo(
                    Todo(
                        id = todo?.id ?: 0,
                        title = title,
                        description = description,
                        completed = completed,
                        dueDate = dueDate,
                        priority = priority
                    )
                )
                navController.popBackStack()
            }) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Save Todo")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("Completed:")
                Checkbox(
                    checked = completed,
                    onCheckedChange = { completed = it }
                )
                Text(
                    text = if (completed) "Hoàn thành" else "Chưa hoàn thành",
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (completed) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                )
            }

            Text("Priority: ", style = MaterialTheme.typography.bodyLarge)
            Row(
                modifier = Modifier
                    .size(240.dp)
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Absolute.SpaceAround
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    RadioButton(selected = priority == "low", onClick = { priority = "low" })
                    Text("Low", style = MaterialTheme.typography.bodyMedium)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    RadioButton(selected = priority == "medium", onClick = { priority = "medium" })
                    Text("Medium", style = MaterialTheme.typography.bodyMedium)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    RadioButton(selected = priority == "high", onClick = { priority = "high" })
                    Text("High", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}