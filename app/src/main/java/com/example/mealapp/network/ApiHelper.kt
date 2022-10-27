package com.example.mealapp.network

import com.example.mealapp.data.MealDetailsResponse
import com.example.mealapp.data.MealResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun getMeals(): Response<MealResponse>

    suspend fun getMealDetails(mealId: Int): Response<MealDetailsResponse>
}