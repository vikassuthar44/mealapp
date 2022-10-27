package com.example.mealapp.network

import com.example.mealapp.data.MealDetailsResponse
import com.example.mealapp.data.MealDetailsResponse.MealDetails
import com.example.mealapp.data.MealResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{

    @GET("filter.php?c=dessert")
    suspend fun getMeals(): Response<MealResponse>

    @GET("lookup.php")
    suspend fun getMealDetails(
        @Query("i") mealId: Int
    ): Response<MealDetailsResponse>
}