package com.example.easycook.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easycook.Model.CategoryDetailsList
import com.example.easycook.Repositry.db_opreation
import com.example.easycook.Retrofite.RestApi
import kotlinx.coroutines.launch

class CategoryFragmentVM():ViewModel() {

    private var repositry= db_opreation(RestApi.api)

    private  var category =MutableLiveData<CategoryDetailsList>()


   fun fetchCategory(){
        viewModelScope.launch {
            category.value=repositry.getCategory()
        }
    }

    fun observeCategory():MutableLiveData<CategoryDetailsList>{
       return category
    }

}