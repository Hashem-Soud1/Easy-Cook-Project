package com.example.easycook.UI.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.easycook.Adapter.MealsApater
import com.example.easycook.ViewModel.HomeFragmentVM
import com.example.easycook.ViewModel.MealsVM
import com.example.easycook.databinding.ActivityMealsBinding


class Meals : AppCompatActivity() {

    private lateinit var binding: ActivityMealsBinding
    private  val vm: MealsVM by viewModels()
    private  val vm2: HomeFragmentVM by viewModels()
    lateinit var adapter: MealsApater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var name=intent.getStringExtra("name")

        vm.fetchMeals(name!!)

        vm.observeMeals().observe(this){

            adapter=MealsApater {

                clikePopularMeal(it)


            }
            adapter.setPopulerMeals(it)

            binding.rec.layoutManager=GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false)
            binding.rec.adapter=adapter

            adapter.notifyDataSetChanged()

        }



    }

    private fun clikePopularMeal(mealId:Int) {
        vm2.fetchPopularMealDetails(mealId)

        vm2.observePopularMealDetails().observe(this){

            val intent= Intent(this,MealDetailes::class.java)
            intent.putExtra("name",it.strMeal)
            intent.putExtra("img",it.strMealThumb)
            intent.putExtra("youtube",it.strYoutube)
            intent.putExtra("intro",it.strInstructions)
            intent.putExtra("area",it.strArea)
            intent.putExtra("category",it.strCategory)
            startActivity(intent)


        }
    }
}