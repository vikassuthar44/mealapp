package com.example.mealapp.data

import com.google.gson.annotations.SerializedName

data class MealDetailsResponse(
    @SerializedName("meals")
    val mealDetails: List<MealDetails>
) {
    data class MealDetails(
        @SerializedName("idMeal")
        val mealId: Int,
        @SerializedName("strMeal")
        val mealName: String,
        @SerializedName("strMealThumb")
        val mealImage: String,
        @SerializedName("strInstructions")
        val mealInstruction: String,
        @SerializedName("")
        val strIngredient: String = "",
        @SerializedName("strIngredient1")
        val strIngredient1: String,
        @SerializedName("strMeasure1")
        val strMeasure1: String,
        @SerializedName("strIngredient2")
        val strIngredient2: String,
        @SerializedName("strMeasure2")
        val strMeasure2: String,
        @SerializedName("strIngredient3")
        val strIngredient3: String,
        @SerializedName("strMeasure3")
        val strMeasure3: String,
        @SerializedName("strIngredient4")
        val strIngredient4: String,
        @SerializedName("strMeasure4")
        val strMeasure4: String,
        @SerializedName("strIngredient5")
        val strIngredient5: String,
        @SerializedName("strMeasure5")
        val strMeasure5: String,
        @SerializedName("strIngredient6")
        val strIngredient6: String,
        @SerializedName("strMeasure6")
        val strMeasure6: String,
        @SerializedName("strIngredient7")
        val strIngredient7: String,
        @SerializedName("strMeasure7")
        val strMeasure7: String,
        @SerializedName("strIngredient8")
        val strIngredient8: String,
        @SerializedName("strMeasure8")
        val strMeasure8: String,
        @SerializedName("strIngredient9")
        val strIngredient9: String,
        @SerializedName("strMeasure9")
        val strMeasure9: String,
        @SerializedName("strIngredient10")
        val strIngredient10: String,
        @SerializedName("strMeasure10")
        val strMeasure10: String,
        @SerializedName("strIngredient11")
        val strIngredient11: String,
        @SerializedName("strMeasure11")
        val strMeasure11: String,
        @SerializedName("strIngredient12")
        val strIngredient12: String,
        @SerializedName("strMeasure12")
        val strMeasure12: String,
        @SerializedName("strIngredient13")
        val strIngredient13: String,
        @SerializedName("strMeasure13")
        val strMeasure13: String,
        @SerializedName("strIngredient14")
        val strIngredient14: String,
        @SerializedName("strMeasure14")
        val strMeasure14: String,
        @SerializedName("strIngredient15")
        val strIngredient15: String,
        @SerializedName("strMeasure15")
        val strMeasure15: String,

    )
}
