package com.example.easycook.Repositry

import android.util.Log
import com.example.easycook.Model.CategoryDetailsList
import com.example.easycook.Model.CategoryList
import com.example.easycook.Model.Meal
import com.example.easycook.Model.MealList
import com.example.easycook.Retrofite.MealApi

 class db_opreation (val api: MealApi) :db_requirements {

     override suspend fun getRandomMeal(): MealList {
         return api.getRandomMeal()
     }

     override suspend fun getPopulerMeals(category: String): CategoryList {
         return api.getPopulerMeals(category)?.takeIf { it.meals != null && it.meals.isNotEmpty() }
             ?: api.getPopulerMeals("pork")
     }

     override suspend fun getMealDetails(mealId: Int): MealList {
         return  api.getMealDetails(mealId)
     }

     override suspend fun getCategory(): CategoryDetailsList {
         return api.getCategory()
     }

     override suspend fun searchMeal(searchWrod:String): CategoryList {
         return api.searchMeal(searchWrod)
     }


 }


