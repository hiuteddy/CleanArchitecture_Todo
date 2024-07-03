package com.fpl.hieunnph32561.myapplication.domain.usecase


import com.fpl.hieunnph32561.myapplication.domain.model.Todo
import com.fpl.hieunnph32561.myapplication.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class GetTodosUseCase(private val repository: TodoRepository) {
    operator fun invoke(): Flow<List<Todo>> {
        return repository.getTodos()
    }
}
