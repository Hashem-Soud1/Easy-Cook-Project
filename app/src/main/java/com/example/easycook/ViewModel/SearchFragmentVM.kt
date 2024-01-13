package com.example.easycook.ViewModel

import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easycook.Model.CategoryList
import com.example.easycook.Model.MealList
import com.example.easycook.Repositry.db_opreation
import com.example.easycook.Retrofite.RestApi
import kotlinx.coroutines.launch

class SearchFragmentVM():ViewModel() {
    private  var search=MutableLiveData<CategoryList>()
    private val repositry =db_opreation(RestApi.api)


    fun fetchSearchMeal(text:String){
        viewModelScope.launch {
            search.value=repositry.searchMeal(text)
        }
    }

    fun observeSearchMeal():MutableLiveData<CategoryList>{return search}

}