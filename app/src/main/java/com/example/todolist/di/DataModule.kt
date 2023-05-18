package com.example.todolist.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.todolist.PrefsRepository
import com.example.todolist.RoomRepository
import com.example.todolist.data.PrefsRepositoryImpl
import com.example.todolist.data.PrefsRepositoryImpl.Companion.PREFS_NAME
import com.example.todolist.data.RoomRepositoryImpl
import com.example.todolist.data.RoomRepositoryImpl.Companion.DATABASE_NAME
import com.example.todolist.room.AppDatabase
import com.example.todolist.room.ToDoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Singleton
    @Provides
    fun providesRoomDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun providesToDoDao(appDatabase: AppDatabase) = appDatabase.toDoDao()

    @Singleton
    @Provides
    fun providesSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun providesRoomRepository(toDoDao: ToDoDao): RoomRepository = RoomRepositoryImpl(toDoDao)

    @Singleton
    @Provides
    fun providesPrefsRepository(sharedPreferences: SharedPreferences): PrefsRepository = PrefsRepositoryImpl(sharedPreferences)
}