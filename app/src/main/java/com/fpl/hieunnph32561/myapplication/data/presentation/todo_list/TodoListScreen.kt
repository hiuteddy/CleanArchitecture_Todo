package com.fpl.hieunnph32561.myapplication.data.presentation.todo_detail

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fpl.hieunnph32561.myapplication.data.presentation.todo_list.TodoListViewModel
import com.fpl.hieunnph32561.myapplication.domain.model.Todo
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TodoListScreen(navController: NavController, viewModel: TodoListViewModel = hiltViewModel()) {
    val todos by viewModel.todos.collectAsState()
    var searchText by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Todo List") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("todo_detail") }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Todo")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            SearchTextField(
                value = searchText,
                onValueChange = { searchText = it },
                placeholder = "Search",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(todos.filter {
                    it.title.contains(searchText.text, ignoreCase = true) || it.description.contains(
                        searchText.text,
                        ignoreCase = true
                    )
                }) { todo ->
                    TodoItem(todo, navController) {
                        viewModel.deleteTodo(todo)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoItem(todo: Todo, navController: NavController, onDeleteClick: () -> Unit) {
    val dateFormat = SimpleDateFormat(" HH:mm", Locale.getDefault())
    val formattedDate = dateFormat.format(Date(todo.dueDate ?: 0))

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { navController.navigate("todo_detail/${todo.id}") },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("Tiêu đề: ${todo.title}", style = MaterialTheme.typography.titleLarge)
            Text("Mô tả: ${todo.description}", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(vertical = 4.dp))
            Text("Ưu tiên: ${todo.priority.capitalize()}", style = MaterialTheme.typography.bodySmall)
            Text("Date: $formattedDate", style = MaterialTheme.typography.bodySmall)
            Text(
                text = if (todo.completed) "Hoàn thành" else "Chưa hoàn thành",
                style = MaterialTheme.typography.bodySmall,
                color = if (todo.completed) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = onDeleteClick) {
                    Text("Xóa", color = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}

@Composable
fun SearchTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon"
            )
        },
        modifier = modifier
    )
}
