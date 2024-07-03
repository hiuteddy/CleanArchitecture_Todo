package com.fpl.hieunnph32561.myapplication.data.presentation.todo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fpl.hieunnph32561.myapplication.domain.model.Todo
import com.fpl.hieunnph32561.myapplication.domain.usecase.DeleteTodoUseCase
import com.fpl.hieunnph32561.myapplication.domain.usecase.GetTodosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val getTodosUseCase: GetTodosUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase // Inject delete use case
) : ViewModel() {

    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos: StateFlow<List<Todo>> = _todos

    init {
        getTodos()
    }

    private fun getTodos() {
        viewModelScope.launch {
            getTodosUseCase().collect {
                _todos.value = it
            }
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            deleteTodoUseCase(todo)
            // After deletion, refresh the list
            getTodos()
        }
    }
}
