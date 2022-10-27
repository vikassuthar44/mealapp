package com.example.mealapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DatabaseDao {

    @Insert(onConflict = IGNORE)
    fun addMeals(mealsEntity: MealsEntity)

    @Query("SELECT * FROM meals_data")
    fun getMeals(): Flow<List<MealsEntity>>

    @Insert(onConflict = IGNORE)
    fun addMealDetails(mealDetailsEntity: MealDetailsEntity)

    @Query("SELECT * FROM meal_details WHERE mealId =:mealId")
    fun getMealsDetails(mealId: Int): Flow<MealDetailsEntity?>

}