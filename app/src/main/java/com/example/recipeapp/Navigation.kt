package com.example.recipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController){
    val recipeViewModel: MainViewModel = viewModel()
    val recipeState by recipeViewModel.categoryState

    NavHost(navController = navController, startDestination = Screen.HomeScreen.route){
//   this part is responsible for passing data from the current screen to detail screen
        //It utilizes the savedstate handle, which is a component of the compose navigation framework
        composable(route = Screen.HomeScreen.route) {
            RecipeScreen(Modifier, recipeState, navigateToDetail = {
                navController.currentBackStackEntry?.savedStateHandle?.set("cat", it)
                navController.navigate(Screen.DetailScreen.route)
            })
        }
        composable(route = Screen.DetailScreen.route) {
            val category = navController.previousBackStackEntry?.savedStateHandle?.
                    get<CategoryX>("cat") ?: CategoryX("", "", "")
            CategoryDetailScreen(categoryX = category)
        }
    }
}