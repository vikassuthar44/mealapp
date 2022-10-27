package com.example.mealapp.data

import com.google.gson.annotations.SerializedName

data class MealResponse(
    @SerializedName("meals")
    val meals: List<Meals>
) {

    data class Meals(
        @SerializedName("strMeal")
        val mealName: String,
        @SerializedName("strMealThumb")
        val mealImage: String,
        @SerializedName("idMeal")
        val mealId: String
    )
}
