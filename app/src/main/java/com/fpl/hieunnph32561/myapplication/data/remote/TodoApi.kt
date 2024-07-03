package com.fpl.hieunnph32561.myapplication.data.remote

import com.fpl.hieunnph32561.myapplication.domain.model.Todo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface TodoApi {
//    @GET("/")
//    suspend fun getTodos(): List<Todo>
//
//    @GET("todos/{id}")
//    suspend fun getTodoById(@Path("id") id: Int): Todo

    @POST("add")
    suspend fun createTodo(@Body todo: Todo): Response<Todo>

    //    @PATCH("update/{id}")
//    suspend fun updateTodo(@Path("id") id: Int, @Body todo: Todo): Response<Todo>
    @PATCH("update/{id}")
    suspend fun updateTodo(@Path("id") id: String, @Body todo: Todo): Response<Todo>

    //    @DELETE("delete/{id}")
//    suspend fun deleteTodo(@Path("id") id: String)
    @DELETE("delete/{id}")
    suspend fun deleteTodo(@Path("id") id: String): Response<Unit>

}
