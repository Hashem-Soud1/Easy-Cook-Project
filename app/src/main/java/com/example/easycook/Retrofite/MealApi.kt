package com.example.easycook.Retrofite

import com.example.easycook.Model.CategoryDetailsList
import com.example.easycook.Model.CategoryList
import com.example.easycook.Model.Meal
import com.example.easycook.Model.MealList
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("random.php")
    suspend fun getRandomMeal():MealList

    @GET("filter.php?")
    suspend fun getPopulerMeals(@Query("i") category: String):CategoryList

    @GET("lookup.php?")
    suspend fun getMealDetails(@Query("i") id: Int):MealList

     @GET("categories.php")
     suspend fun getCategory():CategoryDetailsList

     @GET("search.php?")
     suspend fun searchMeal(@Query("s") search:String) :CategoryList






}