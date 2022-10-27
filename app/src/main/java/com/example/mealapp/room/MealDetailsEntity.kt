package com.example.mealapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal_details")
data class MealDetailsEntity(
    @PrimaryKey
    val mealId: Int,
    val mealImage: String,
    val mealName: String,
    val mealInstruction: String,
    val mealStrIngredient1: String,
    val mealStrIngredient2: String,
    val mealStrIngredient3: String,
    val mealStrIngredient4: String,
    val mealStrIngredient5: String,
    val mealStrIngredient6: String,
    val mealStrIngredient7: String,
    val mealStrIngredient8: String,
    val mealStrIngredient9: String,
    val mealStrIngredient10: String,
    val mealStrIngredient11: String,
    val mealStrIngredient12: String,
    val mealStrIngredient13: String,
    val mealStrIngredient14: String,
    val mealStrIngredient15: String,
    val mealStrMeasure1: String,
    val mealStrMeasure2: String,
    val mealStrMeasure3: String,
    val mealStrMeasure4: String,
    val mealStrMeasure5: String,
    val mealStrMeasure6: String,
    val mealStrMeasure7: String,
    val mealStrMeasure8: String,
    val mealStrMeasure9: String,
    val mealStrMeasure10: String,
    val mealStrMeasure11: String,
    val mealStrMeasure12: String,
    val mealStrMeasure13: String,
    val mealStrMeasure14: String,
    val mealStrMeasure15: String,
)