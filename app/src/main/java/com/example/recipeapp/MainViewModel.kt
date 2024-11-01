package com.example.recipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _categoryState = mutableStateOf(RecipeState())
    val categoryState: State<RecipeState> = _categoryState

    init {
        fetchCategories()
    }

    private fun fetchCategories(){
        viewModelScope.launch {
            try {
                val response = recipeService.getMealCategories()
                _categoryState.value = _categoryState.value.copy(
                    loading = false,
                    categoryList = response.categories,
                    error = null
                )
            }catch (e:Exception){
                _categoryState.value = _categoryState.value.copy(
                    loading = false,
                    error = "Failed to fecth categories. ${e.localizedMessage}"
                )
            }
        }
    }

    data class RecipeState(
        val loading: Boolean = true,
        val categoryList: List<CategoryX> = emptyList(),
        val error: String? = null
    )
}