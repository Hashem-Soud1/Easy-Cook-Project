package com.example.easycook.UI.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.easycook.Adapter.MealsApater
import com.example.easycook.Model.Meal
import com.example.easycook.R
import com.example.easycook.UI.activites.MealDetailes
import com.example.easycook.ViewModel.HomeFragmentVM
import com.example.easycook.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

private lateinit var binding :FragmentHomeBinding

    private val vm :HomeFragmentVM by viewModels()
    private lateinit var meal:Meal
    private lateinit var populer_adapter:MealsApater
    private lateinit var rec:RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding=FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // this.rec =view.findViewById<RecyclerView>(R.id.rec_populeritem)
//
          getRandomMeal()
          getPupulerMeals()

        binding.randomMealImg.setOnClickListener {
            goRandomMealDetailes()
        }





        binding.searchBar.setOnClickListener{
findNavController().navigate(R.id.action_home_to_searchFragment)
      }


        }
    private fun getPupulerMeals() {
        vm.fetchPupulerMeals()
        vm.observePupulerMeals().observe(viewLifecycleOwner){
            popularmeal ->

    populer_adapter = MealsApater(requireContext()) { mealId ->
         clikePopularMeal(mealId)
    }
    populer_adapter.setPopulerMeals(popularmeal)

    val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    binding.recPopuleritem.layoutManager = layoutManager
    binding.recPopuleritem.adapter = populer_adapter
    populer_adapter.notifyDataSetChanged()



        }
    }

    private fun clikePopularMeal(mealId:Int) {
       vm.fetchPopularMealDetails(mealId)

        vm.observePopularMealDetails().observe(viewLifecycleOwner){

            val intent= Intent(activity,MealDetailes::class.java)
            intent.putExtra("name",it.strMeal)
            intent.putExtra("img",it.strMealThumb)
            intent.putExtra("youtube",it.strYoutube)
            intent.putExtra("intro",it.strInstructions)
            intent.putExtra("area",it.strArea)
            intent.putExtra("category",it.strCategory)
            startActivity(intent)
        }
    }


    private fun getRandomMeal() {
        vm.fetchRandomMeal()

        vm.observeRandomMeal().observe(viewLifecycleOwner) {
                meal -> meal?.let {
            Glide.with(this@HomeFragment)
                .load(it.strMealThumb)
                .into(binding.randomMealImg)
        }
        this.meal=meal
        }
    }

    private fun goRandomMealDetailes() {


        val intent= Intent(activity,MealDetailes::class.java)
        intent.putExtra("name",meal.strMeal)
        intent.putExtra("img",meal.strMealThumb)
        intent.putExtra("youtube",meal.strYoutube)
        intent.putExtra("intro",meal.strInstructions)
        intent.putExtra("area",meal.strArea)
        intent.putExtra("category",meal.strCategory)
        startActivity(intent)

    }
}
