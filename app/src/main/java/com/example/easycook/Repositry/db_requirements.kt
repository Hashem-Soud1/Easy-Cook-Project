package com.example.easycook.Repositry

import com.example.easycook.Model.CategoryDetailsList
import com.example.easycook.Model.CategoryList
import com.example.easycook.Model.Meal
import com.example.easycook.Model.MealList

interface db_requirements {
    suspend fun getRandomMeal(): MealList
    suspend fun getPopulerMeals(category: String):CategoryList
    suspend fun getMealDetails(mealId:Int): MealList
    suspend fun getCategory():CategoryDetailsList
    suspend fun searchMeal(searchWrod:String):CategoryList


}