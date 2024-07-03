package com.fpl.hieunnph32561.myapplication.di

import android.app.Application
import androidx.room.Room
import com.fpl.hieunnph32561.myapplication.data.local.TodoDao
import com.fpl.hieunnph32561.myapplication.data.local.TodoDatabase
import com.fpl.hieunnph32561.myapplication.data.remote.ApiService
import com.fpl.hieunnph32561.myapplication.data.remote.TodoApi
import com.fpl.hieunnph32561.myapplication.data.repository.TodoRepositoryImpl
import com.fpl.hieunnph32561.myapplication.domain.repository.TodoRepository
import com.fpl.hieunnph32561.myapplication.domain.usecase.AddTodoUseCase
import com.fpl.hieunnph32561.myapplication.domain.usecase.DeleteTodoUseCase
import com.fpl.hieunnph32561.myapplication.domain.usecase.GetTodoByIdUseCase
import com.fpl.hieunnph32561.myapplication.domain.usecase.GetTodosUseCase
import com.fpl.hieunnph32561.myapplication.domain.usecase.UpdateTodoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(app: Application): TodoDatabase {
        return Room.databaseBuilder(
            app,
            TodoDatabase::class.java,
            "todos"
        ).fallbackToDestructiveMigration() // Reset và tạo lại cơ sở dữ liệu khi cần

            .build()
    }

    @Provides
    @Singleton
    fun provideTodoDao(db: TodoDatabase) = db.todoDao()

    @Provides
    @Singleton
    fun provideTodoApi(): TodoApi {
        return ApiService.todoApi
    }

    @Provides
    @Singleton
    fun provideTodoRepository(api: TodoApi, dao: TodoDao): TodoRepository {
        return TodoRepositoryImpl(dao, api)
    }

    @Provides
    @Singleton
    fun provideGetTodosUseCase(repository: TodoRepository): GetTodosUseCase {
        return GetTodosUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAddTodoUseCase(repository: TodoRepository): AddTodoUseCase {
        return AddTodoUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideUpdateTodoUseCase(repository: TodoRepository): UpdateTodoUseCase {
        return UpdateTodoUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteTodoUseCase(repository: TodoRepository): DeleteTodoUseCase {
        return DeleteTodoUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetTodoByIdUseCase(repository: TodoRepository): GetTodoByIdUseCase {
        return GetTodoByIdUseCase(repository)
    }
}
