package com.example.easycook.Room_db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.easycook.Model.Meal

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upInsertFav(meal: Meal)

    @Delete
    suspend fun deleteMealFav(meal:Meal)

    @Query("SELECT * FROM mealInformation")
    fun getAllSavedMeals(): LiveData<List<Meal>>







}