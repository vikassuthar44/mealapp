package com.example.mealapp.mealdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealapp.network.MainRepository
import com.example.mealapp.room.DatabaseRepository
import com.example.mealapp.room.MealDetailsEntity
import com.example.mealapp.util.RequestState
import com.example.mealapp.util.RequestState.Idle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsDetailsViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val databaseRepository: DatabaseRepository
) : ViewModel() {

    private val _mealDetail = MutableStateFlow<RequestState<MealDetailsEntity>>(Idle)

    val mealDetail = _mealDetail.asStateFlow()

    fun tryAgain(mealId: Int) {
        getMealsDetails(mealId = mealId)
    }

    fun getMealsDetails(mealId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _mealDetail.value = RequestState.Loading
                databaseRepository.getMealDetails(mealId).collect() { mealDetail ->
                    if (mealDetail == null) {
                        mainRepository.getMealDetail(mealId = mealId).let {
                            if (it.isSuccessful) {
                                val meal = it.body()?.mealDetails?.get(0)
                                val mealDetailsEntity = meal?.let { mealData ->
                                    MealDetailsEntity(
                                        mealId = mealData.mealId,
                                        mealName = mealData.mealName,
                                        mealImage = mealData.mealImage,
                                        mealInstruction = meal.mealInstruction,
                                        mealStrIngredient1 = meal.strIngredient1,
                                        mealStrIngredient2 = meal.strIngredient2,
                                        mealStrIngredient3 = meal.strIngredient3,
                                        mealStrIngredient4 = meal.strIngredient4,
                                        mealStrIngredient5 = meal.strIngredient5,
                                        mealStrIngredient6 = meal.strIngredient6,
                                        mealStrIngredient7 = meal.strIngredient7,
                                        mealStrIngredient8 = meal.strIngredient8,
                                        mealStrIngredient9 = meal.strIngredient9,
                                        mealStrIngredient10 = meal.strIngredient10,
                                        mealStrIngredient11 = meal.strIngredient11,
                                        mealStrIngredient12 = meal.strIngredient12,
                                        mealStrIngredient13 = meal.strIngredient13,
                                        mealStrIngredient14 = meal.strIngredient14,
                                        mealStrIngredient15 = meal.strIngredient15,
                                        mealStrMeasure1 = meal.strMeasure1,
                                        mealStrMeasure2 = meal.strMeasure2,
                                        mealStrMeasure3 = meal.strMeasure3,
                                        mealStrMeasure4 = meal.strMeasure4,
                                        mealStrMeasure5 = meal.strMeasure5,
                                        mealStrMeasure6 = meal.strMeasure6,
                                        mealStrMeasure7 = meal.strMeasure7,
                                        mealStrMeasure8 = meal.strMeasure8,
                                        mealStrMeasure9 = meal.strMeasure9,
                                        mealStrMeasure10 = meal.strMeasure10,
                                        mealStrMeasure11 = meal.strMeasure11,
                                        mealStrMeasure12 = meal.strMeasure12,
                                        mealStrMeasure13 = meal.strMeasure13,
                                        mealStrMeasure14 = meal.strMeasure14,
                                        mealStrMeasure15 = meal.strMeasure15,
                                    )
                                }
                                mealDetailsEntity?.let { it1 -> databaseRepository.addMealDetails(it1) }
                                databaseRepository.getMealDetails(mealId = mealId).collect() {
                                    _mealDetail.value = RequestState.Success(it)
                                }

                            } else {
                                _mealDetail.value = RequestState.Error("Something Went Wrong")
                            }
                        }
                    } else {
                        databaseRepository.getMealDetails(mealId = mealId).collect() {
                            _mealDetail.value = RequestState.Success(it)
                        }
                    }
                }
            } catch (e: Exception) {
                _mealDetail.value = RequestState.Error("Something Wrong")
            }
        }
    }
}