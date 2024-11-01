package com.example.recipeapp

sealed class Screen(val route: String) {
    object HomeScreen: Screen("homescreen")
    object DetailScreen: Screen("detailscreen")
}