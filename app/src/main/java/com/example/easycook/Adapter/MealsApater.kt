package com.example.easycook.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.easycook.Model.CategoryList
import com.example.easycook.UI.activites.Meals
import com.example.easycook.UI.fragment.HomeFragment
import com.example.easycook.UI.fragment.SearchFragment
import com.example.easycook.ViewModel.HomeFragmentVM
import com.example.easycook.databinding.MealItemBinding


class MealsApater( val context: Context? = null ,private val onItemClickListener: (Int) -> Unit): RecyclerView.Adapter<MealsApater.PopulerVH>() {

    constructor(onItemClickListener: (Int) -> Unit) : this(null, onItemClickListener) {}
    private lateinit var populerList : CategoryList


    fun setPopulerMeals( p :CategoryList){
        this.populerList=p
    }

    inner class PopulerVH (val binding:MealItemBinding):RecyclerView.ViewHolder(binding.root) { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopulerVH {
         val binding=MealItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PopulerVH(binding)
    }

    override fun getItemCount(): Int {
        return populerList?.meals?.size ?: 0
    }

    override fun onBindViewHolder(holder: PopulerVH, position: Int) {

        val img =populerList.meals[position].strMealThumb
        val mealid =populerList.meals[position].idMeal.toInt()

         if (context==null) {
             holder.binding.mealName.visibility=View.VISIBLE
             holder.binding.mealName.text=populerList.meals[position].strMeal
         }





        Glide.with(holder.itemView.context)
            .load(img)
            .into(holder.binding.imgPopularMeal)

        holder.itemView.setOnClickListener{
            onItemClickListener(mealid)
        }

    }
}