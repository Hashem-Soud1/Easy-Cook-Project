package com.example.easycook.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easycook.Model.CategoryList
import com.example.easycook.Model.Meal
import com.example.easycook.Model.MealList
import com.example.easycook.Repositry.db_opreation
import com.example.easycook.Retrofite.RestApi
import com.example.easycook.Room_db.mealDatabase
import kotlinx.coroutines.launch


class MealsVM(
    val mealdb: mealDatabase.Companion =mealDatabase
):ViewModel() {

    private var repositry= db_opreation(RestApi.api)
    private  var meal=MutableLiveData<CategoryList>()

    fun fetchMeals(name:String){
        viewModelScope.launch {
            meal.value=repositry.getPopulerMeals(name)

        }
    }
    fun observeMeals():MutableLiveData<CategoryList>{return  meal}

//    fun upsertMeal(meal: Meal){
//        viewModelScope.launch {
//            mealdb.instance!!.mealDao().upInsertFav(meal)
//        }
//    }
//
//    fun deleteMeal(meal: Meal){
//        viewModelScope.launch {
//            mealdb.instance!!.mealDao().deleteMealFav(meal)
//        }
//    }


}
