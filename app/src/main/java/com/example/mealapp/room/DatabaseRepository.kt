package com.example.mealapp.room

import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {

    suspend fun getMeals(): Flow<List<MealsEntity>>

    suspend fun addMeals(mealsEntity: MealsEntity)

    suspend fun addMealDetails(mealDetailsEntity: MealDetailsEntity)

    suspend fun getMealDetails(mealId:Int): Flow<MealDetailsEntity?>
}