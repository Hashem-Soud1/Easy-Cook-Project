package com.example.easycook.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easycook.Model.Category
import com.example.easycook.Model.CategoryList
import com.example.easycook.Model.Meal
import com.example.easycook.Repositry.db_opreation
import com.example.easycook.Retrofite.RestApi
import kotlinx.coroutines.launch
import kotlin.math.log

class HomeFragmentVM():ViewModel()  {

    private var repositry=db_opreation(RestApi.api)

    private var randomMeal=MutableLiveData<Meal>()
    private var pupulerMeal=MutableLiveData<CategoryList>()
    private var detailsMeal=MutableLiveData<Meal>()

    var categoryName:String=""

     //Random Meal
fun fetchRandomMeal(){
    viewModelScope.launch {
        randomMeal.value =repositry.getRandomMeal().meals[0]
        categoryName= repositry.getRandomMeal().meals[0].strCategory.toString()
    }
   }
      fun observeRandomMeal():MutableLiveData<Meal>{ return randomMeal }

    //Pupuler Meals
fun fetchPupulerMeals(){
    viewModelScope.launch {

        pupulerMeal.value = repositry.getPopulerMeals(categoryName)


    }
}
    fun observePupulerMeals():MutableLiveData<CategoryList>{ return pupulerMeal }

    // Meal details
    fun fetchPopularMealDetails(mealId:Int) {
        viewModelScope.launch {
            detailsMeal.value = repositry.getMealDetails(mealId).meals[0]

        }
    }
    fun observePopularMealDetails():MutableLiveData<Meal>{
        return detailsMeal
    }


}