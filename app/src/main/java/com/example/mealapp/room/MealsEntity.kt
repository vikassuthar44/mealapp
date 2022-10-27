package com.example.mealapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meals_data")
data class MealsEntity(
    @PrimaryKey
    val mealId: Int,
    val mealImage: String,
    val mealName: String
)
