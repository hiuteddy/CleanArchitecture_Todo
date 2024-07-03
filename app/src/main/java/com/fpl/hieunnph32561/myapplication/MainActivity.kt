package com.fpl.hieunnph32561.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fpl.hieunnph32561.myapplication.data.presentation.todo_detail.TodoDetailScreen
import com.fpl.hieunnph32561.myapplication.data.presentation.todo_detail.TodoListScreen
import com.fpl.hieunnph32561.myapplication.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "todo_list") {
                        composable("todo_list") {
                            TodoListScreen(navController)
                        }
                        composable("todo_detail/{todoId}") { backStackEntry ->
                            val todoId = backStackEntry.arguments?.getString("todoId")?.toInt()
                            TodoDetailScreen(navController, todoId = todoId)
                        }
                        composable("todo_detail") {
                            TodoDetailScreen(navController)
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        MyApplicationTheme {
            Greeting("Android")
        }
    }
}
