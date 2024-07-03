package com.fpl.hieunnph32561.myapplication.data.presentation.todo_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fpl.hieunnph32561.myapplication.domain.model.Todo
import com.fpl.hieunnph32561.myapplication.domain.usecase.AddTodoUseCase
import com.fpl.hieunnph32561.myapplication.domain.usecase.GetTodoByIdUseCase
import com.fpl.hieunnph32561.myapplication.domain.usecase.UpdateTodoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoDetailViewModel @Inject constructor(
    private val getTodoByIdUseCase: GetTodoByIdUseCase,
    private val addTodoUseCase: AddTodoUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase
) : ViewModel() {

    private val _todo = MutableStateFlow<Todo?>(null)
    val todo: StateFlow<Todo?> = _todo

    fun loadTodo(id: Int) {
        viewModelScope.launch {
            _todo.value = getTodoByIdUseCase(id)
        }
    }

    fun addOrUpdateTodo(todo: Todo) {
        viewModelScope.launch {
            if (todo.id == 0) {
                addTodoUseCase(todo)
            } else {
                updateTodoUseCase(todo)
            }
        }
    }
}
