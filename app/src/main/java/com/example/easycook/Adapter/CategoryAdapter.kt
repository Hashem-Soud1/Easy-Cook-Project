package com.example.easycook.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.easycook.Model.CategoryDetailsList
import com.example.easycook.Model.CategoryList
import com.example.easycook.databinding.CategoryItemBinding


class CategoryAdapter(private val onItemClickListener: (String) -> Unit) :

    RecyclerView.Adapter<CategoryAdapter.CategoryVH>() {

    private lateinit var categoryList : CategoryDetailsList

    fun setCategory( p : CategoryDetailsList){
        this.categoryList=p
    }

    inner class CategoryVH (val binding: CategoryItemBinding):RecyclerView.ViewHolder(binding.root) { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
        val binding= CategoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoryVH(binding)
    }

    override fun getItemCount(): Int {
        return categoryList?.categories?.size ?: 0
    }

    override fun onBindViewHolder(holder: CategoryVH, position: Int) {

        val img =categoryList.categories[position].strCategoryThumb
        val Categoryid =categoryList.categories[position].strCategory

        holder.binding.nameCategroy.text=categoryList.categories[position].strCategory

        Glide.with(holder.itemView.context)
            .load(img)
            .into(holder.binding.imgCategory)

        holder.itemView.setOnClickListener{
            onItemClickListener(Categoryid)
        }

    }
}