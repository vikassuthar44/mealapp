package com.example.mealapp.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.style.TextOverflow.Companion
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.mealapp.mealdetails.MealDetails
import com.example.mealapp.room.MealsEntity
import com.example.mealapp.ui.theme.MealAppTheme
import com.example.mealapp.util.RequestStateRender
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealAppTheme {
                MainContent()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent() {
    val context = LocalContext.current
    val homeViewModel = hiltViewModel<HomeViewModel>()
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
                        text = "The Meal DB",
                        color = Color.Black
                    )
                }
            }
        }
    ) {
        RequestStateRender(
            state = homeViewModel.mealsData.collectAsState(),
            onSuccess = {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2)) {
                    itemsIndexed(items = it) { index, item ->
                        SingleMealItem(item = item) { mealId ->
                            val intent = Intent(context, MealDetails::class.java)
                            intent.putExtra("mealId", mealId)
                            context.startActivity(intent)
                        }
                    }
                }
                Log.d("MainActivity", "MainContent: Sucess $it")
            },
            onError = {
               Box(modifier = Modifier.fillMaxSize(),
               contentAlignment = Alignment.Center) {
                   Column(horizontalAlignment = Alignment.CenterHorizontally) {
                       Text(text = "Went Something Wrong!")
                       Spacer(modifier = Modifier.height(20.dp))
                       Button(onClick = {
                           homeViewModel.tryAgain()
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
fun SingleMealItem(
    item: MealsEntity,
    onClick: (Int) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = Color.Gray)
            .padding(all = 10.dp)
            .clickable {
                onClick.invoke(item.mealId)
            },
        shape = RoundedCornerShape(size = 10.dp)
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            val thumbnail = rememberAsyncImagePainter(model = item.mealImage)
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                painter = thumbnail,
                contentDescription = "image")
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = item.mealName, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(5.dp))
        }

    }
}