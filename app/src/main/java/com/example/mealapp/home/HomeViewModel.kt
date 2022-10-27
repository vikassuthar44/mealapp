package com.example.mealapp.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealapp.network.MainRepository
import com.example.mealapp.room.DatabaseRepository
import com.example.mealapp.room.MealsEntity
import com.example.mealapp.util.RequestState
import com.example.mealapp.util.RequestState.Idle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val databaseRepository: DatabaseRepository
) : ViewModel() {

    private val _mealsData = MutableStateFlow<RequestState<List<MealsEntity>>>(Idle)

    val mealsData = _mealsData.asStateFlow()

    init {
        getMeals()
    }

    fun tryAgain() {
        getMeals()
    }

    private fun getMeals() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _mealsData.value = RequestState.Loading
                databaseRepository.getMeals().collect() {
                    if (it.isEmpty()) {
                        mainRepository.getVideos().let { response ->
                            if (response.isSuccessful) {
                                for (meal in response.body()!!.meals) {
                                    val mealsEntity = MealsEntity(
                                        mealId = meal.mealId.toInt(),
                                        mealImage = meal.mealImage,
                                        mealName = meal.mealName
                                    )
                                    databaseRepository.addMeals(mealsEntity)
                                }
                                databaseRepository.getMeals().collect() { mealsList ->
                                    _mealsData.value = RequestState.Success(mealsList)
                                }
                            } else {
                                _mealsData.value = RequestState.Error(response.errorBody().toString())
                            }
                        }
                    } else {
                        databaseRepository.getMeals().collect() { mealsList ->
                            _mealsData.value = RequestState.Success(mealsList)
                        }
                    }
                }
            } catch (e: Exception) {
                _mealsData.value = RequestState.Error("Something Wrong")
            }
        }

    }
}