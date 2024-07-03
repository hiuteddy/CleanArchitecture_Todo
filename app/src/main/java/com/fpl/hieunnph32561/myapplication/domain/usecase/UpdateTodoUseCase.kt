package com.fpl.hieunnph32561.myapplication.domain.usecase

import com.fpl.hieunnph32561.myapplication.domain.model.Todo
import com.fpl.hieunnph32561.myapplication.domain.repository.TodoRepository


class UpdateTodoUseCase(private val repository: TodoRepository) {
    suspend operator fun invoke(todo: Todo) {
        repository.updateTodo(todo)
    }
}
