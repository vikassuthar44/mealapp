package com.example.mealapp.room



import kotlinx.coroutines.flow.Flow

class DatabaseRepositoryImpl constructor(
    private val databaseDao: DatabaseDao
): DatabaseRepository {

    override suspend fun getMeals(): Flow<List<MealsEntity>> = databaseDao.getMeals()

    override suspend fun addMeals(mealsEntity: MealsEntity) = databaseDao.addMeals(mealsEntity)

    override suspend fun addMealDetails(mealDetailsEntity: MealDetailsEntity) = databaseDao.addMealDetails(mealDetailsEntity = mealDetailsEntity)

    override suspend fun getMealDetails(mealId: Int): Flow<MealDetailsEntity?> = databaseDao.getMealsDetails(mealId = mealId)
}