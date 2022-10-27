package com.example.mealapp.mealdetails

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.mealapp.room.MealDetailsEntity
import com.example.mealapp.ui.theme.MealAppTheme
import com.example.mealapp.util.RequestStateRender
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealDetails : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealAppTheme() {
                MainContent(mealId = intent.getIntExtra("mealId", 0))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(mealId: Int) {
    val context = LocalContext.current
    val mealsDetailsViewModel = hiltViewModel<MealsDetailsViewModel>()

    LaunchedEffect(key1 = Unit) {
        mealsDetailsViewModel.getMealsDetails(mealId = mealId)
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .background(color = Color.White)
                    .padding(all = 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Meal Details",
                        color = Color.Black
                    )
                }
            }
        }
    ) {
        RequestStateRender(
            state = mealsDetailsViewModel.mealDetail.collectAsState(),
            onSuccess = {
                MealDetailUI(meal = it)
            },
            onError = {
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Went Something Wrong!")
                        Spacer(modifier = Modifier.height(20.dp))
                        Button(onClick = {
                            mealsDetailsViewModel.tryAgain(mealId = mealId)
                        }
                        ) {
                            Text(text = "Try Again!")
                        }
                    }

                }
            },
            onLoading = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .zIndex(1f)
                        .padding(top = 24.dp)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Loading...")
                }
            }
        )
    }
}

@Composable
fun MealDetailUI(meal: MealDetailsEntity) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(all = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = meal.mealName, style = TextStyle(fontWeight = FontWeight.Bold))
        val thumbnail = rememberAsyncImagePainter(model = meal.mealImage)
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            painter = thumbnail,
            contentDescription = "image"
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Ingredients", style = TextStyle(fontWeight = FontWeight.Bold))
        Spacer(modifier = Modifier.height(20.dp))
        SetIngredientValue(meal = meal)
        Text(text = "Instructions", style = TextStyle(fontWeight = FontWeight.Bold))
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = meal.mealInstruction)
    }
}

@Composable
fun SetIngredientValue(meal: MealDetailsEntity) {
    Box(modifier = Modifier.padding(horizontal = 20.dp)) {
        Column {
            if (meal.mealStrIngredient1.isNotEmpty()) {
                Row(modifier = Modifier.height(20.dp)) {
                    Text(text = " •" + meal.mealStrMeasure1)
                    Text(text = " " + meal.mealStrIngredient1)
                }
                if (meal.mealStrIngredient2.isNotEmpty()) {
                    Text(text = " •" + meal.mealStrMeasure2)
                    Text(text = " " + meal.mealStrIngredient2)
                }
            }
            if (meal.mealStrIngredient3.isNotEmpty()) {
                Row(modifier = Modifier.height(20.dp)) {
                    Text(text = " •" + meal.mealStrMeasure3)
                    Text(text = " " + meal.mealStrIngredient3)
                }
                if (meal.mealStrIngredient4.isNotEmpty()) {
                    Text(text = " •" + meal.mealStrMeasure4)
                    Text(text = " " + meal.mealStrIngredient4)
                }
            }
            if (meal.mealStrIngredient5.isNotEmpty()) {
                Row(modifier = Modifier.height(20.dp)) {
                    Text(text = " •" + meal.mealStrMeasure5)
                    Text(text = " " + meal.mealStrIngredient5)
                }
                if (meal.mealStrIngredient6.isNotEmpty()) {
                    Text(text = " •" + meal.mealStrMeasure6)
                    Text(text = " " + meal.mealStrIngredient6)
                }

            }
            if (meal.mealStrIngredient7.isNotEmpty()) {
                Row(modifier = Modifier.height(20.dp)) {
                    Text(text = " •" + meal.mealStrMeasure7)
                    Text(text = " " + meal.mealStrIngredient7)
                }
                if (meal.mealStrIngredient8.isNotEmpty()) {
                    Text(text = " •" + meal.mealStrMeasure8)
                    Text(text = " " + meal.mealStrIngredient8)
                }
            }
            if (meal.mealStrIngredient9.isNotEmpty()) {
                Row(modifier = Modifier.height(20.dp)) {
                    Text(text = " •" + meal.mealStrMeasure9)
                    Text(text = " " + meal.mealStrIngredient9)
                }
                if (meal.mealStrIngredient10.isNotEmpty()) {
                    Text(text = " •" + meal.mealStrMeasure10)
                    Text(text = " " + meal.mealStrIngredient10)
                }

            }
            if (meal.mealStrIngredient11.isNotEmpty()) {
                Row(modifier = Modifier.height(20.dp)) {
                    Text(text = " •" + meal.mealStrMeasure11)
                    Text(text = " " + meal.mealStrIngredient11)
                }
                if (meal.mealStrIngredient12.isNotEmpty()) {
                    Text(text = " •" + meal.mealStrMeasure12)
                    Text(text = " " + meal.mealStrIngredient12)
                }

            }
            if (meal.mealStrIngredient13.isNotEmpty()) {
                Row(modifier = Modifier.height(20.dp)) {
                    Text(text = " •" + meal.mealStrMeasure13)
                    Text(text = " " + meal.mealStrIngredient13)
                }
                if (meal.mealStrIngredient14.isNotEmpty()) {
                    Text(text = " •" + meal.mealStrMeasure14)
                    Text(text = " " + meal.mealStrIngredient14)
                }

            }
            if (meal.mealStrIngredient15.isNotEmpty()) {
                Row(modifier = Modifier.height(20.dp)) {
                    Text(text = " •" + meal.mealStrMeasure15)
                    Text(text = " " + meal.mealStrIngredient15)
                }
            }
        }

    }
}