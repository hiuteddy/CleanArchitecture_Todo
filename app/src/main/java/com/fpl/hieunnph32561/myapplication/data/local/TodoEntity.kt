package com.fpl.hieunnph32561.myapplication.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fpl.hieunnph32561.myapplication.domain.model.Todo


@Entity(tableName = "todos")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val completed: Boolean = false,
    val dueDate: Long? = null,
    val priority: String
)

fun TodoEntity.toDomain(): Todo {
    return Todo(
        id = id,
        title = title,
        description = description,
        completed = completed,
        dueDate = dueDate,
        priority = priority
    )
}

fun Todo.toEntity(): TodoEntity {
    return TodoEntity(
        id = id,
        title = title,
        description = description,
        completed = completed,
        dueDate = dueDate,
        priority = priority
    )
}
