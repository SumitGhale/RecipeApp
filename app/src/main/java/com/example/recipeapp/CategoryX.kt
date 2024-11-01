package com.example.recipeapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryX(
    val strCategory: String,
    val strCategoryDescription: String,
    val strCategoryThumb: String
): Parcelable