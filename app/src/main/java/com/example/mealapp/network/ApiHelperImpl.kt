package com.example.mealapp.network

import com.example.mealapp.data.MealDetailsResponse
import com.example.mealapp.data.MealResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
):ApiHelper{
    override suspend fun getMeals(): Response<MealResponse> = apiService.getMeals()

    override suspend fun getMealDetails(mealId: Int): Response<MealDetailsResponse> = apiService.getMealDetails(mealId = mealId)
}