package com.example.mealapp.network


import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiHelper: ApiHelper
){
    suspend fun getVideos() = apiHelper.getMeals()

    suspend fun getMealDetail(mealId: Int) = apiHelper.getMealDetails(mealId = mealId)
}