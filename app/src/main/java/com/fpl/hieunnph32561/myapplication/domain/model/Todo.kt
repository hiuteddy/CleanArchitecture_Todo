package com.fpl.hieunnph32561.myapplication.domain.model


data class Todo(
    val id: Int = 0,
    val title: String,
    val description: String,
    val completed: Boolean = false,
    val dueDate: Long? = null,
    val priority: String
)
