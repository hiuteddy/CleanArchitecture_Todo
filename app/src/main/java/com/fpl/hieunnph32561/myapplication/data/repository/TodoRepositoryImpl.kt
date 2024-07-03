package com.fpl.hieunnph32561.myapplication.data.repository

import com.fpl.hieunnph32561.myapplication.data.local.TodoDao
import com.fpl.hieunnph32561.myapplication.data.local.toDomain
import com.fpl.hieunnph32561.myapplication.data.local.toEntity
import com.fpl.hieunnph32561.myapplication.data.remote.TodoApi
import com.fpl.hieunnph32561.myapplication.domain.model.Todo
import com.fpl.hieunnph32561.myapplication.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class TodoRepositoryImpl(
    private val todoDao: TodoDao,
    private val todoApi: TodoApi
) : TodoRepository {

    override fun getTodos(): Flow<List<Todo>> {
        return todoDao.getAllTodos().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getTodoById(id: Int): Todo? {
        return todoDao.getTodoById(id)?.toDomain()
    }

    override suspend fun addTodo(todo: Todo) {
        todoDao.insert(todo.toEntity())
        todoApi.createTodo(todo)
    }

    override suspend fun updateTodo(todo: Todo) {
        todoDao.update(todo.toEntity())
        todoApi.updateTodo(todo.id.toString(), todo)
    }

    override suspend fun deleteTodo(todo: Todo) {
        todoDao.delete(todo.toEntity())
        todoApi.deleteTodo(todo.id.toString())
    }
}
