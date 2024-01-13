package com.example.easycook.Retrofite

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestApi {

    private const val Base_Url="https://www.themealdb.com/api/json/v1/1/"

  private val instance by lazy {
      Retrofit.Builder().baseUrl(Base_Url)
          .addConverterFactory(GsonConverterFactory.create())
          .build()
  }

    val api :MealApi by lazy {
      instance.create(MealApi::class.java)
    }

}



