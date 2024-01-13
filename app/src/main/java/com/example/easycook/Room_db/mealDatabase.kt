package com.example.easycook.Room_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.easycook.Model.Meal
import com.example.easycook.UI.activites.MealDetailes

@Database(entities = [Meal::class], version = 1)
abstract  class mealDatabase :RoomDatabase() {
    abstract   fun mealDao(): MealDao

    companion object {

    @Volatile
    var instance: mealDatabase? = null

    @Synchronized
    fun getInstance(context: Context): mealDatabase {
        if (instance == null)
            instance = Room.databaseBuilder(
                context, mealDatabase::class.java, "meal.db"
            ).fallbackToDestructiveMigration()
                .build()

        return instance as mealDatabase
    }

}
}