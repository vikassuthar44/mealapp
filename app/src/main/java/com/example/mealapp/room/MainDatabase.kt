package com.example.mealapp.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MealsEntity::class, MealDetailsEntity::class], version = 1, exportSchema = false)
abstract class MainDatabase: RoomDatabase() {
    abstract fun databaseDao(): DatabaseDao
}