package com.fpl.hieunnph32561.myapplication.domain.usecase


import com.fpl.hieunnph32561.myapplication.domain.model.Todo
import com.fpl.hieunnph32561.myapplication.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class GetTodoByIdUseCase(private val repository: TodoRepository) {
    suspend operator fun invoke(id: Int): Todo? {
        return repository.getTodoById(id)
    }
}
