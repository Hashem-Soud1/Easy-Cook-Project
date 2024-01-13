package com.example.easycook.UI.activites

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.easycook.databinding.ActivityMealDetailesBinding


class MealDetailes : AppCompatActivity() {
    private  lateinit var binding :ActivityMealDetailesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMealDetailesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        getAndSetInfoIntent()

        binding.youtub.setOnClickListener(){

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(intent.getStringExtra("youtube")))
            startActivity(intent)


        }

    }


    override fun onPause() {
        super.onPause()
        finish()
    }

    private fun getAndSetInfoIntent() {

        var intent=intent


        binding.categori.text="Category : "+intent.getStringExtra("category")
        binding.location.text="Location : "+intent.getStringExtra("area")
        binding.instruction.text=intent.getStringExtra("intro")
        binding.CollapsingToolbar.title=intent.getStringExtra("name")

        Glide.with(this)
            .load(intent.getStringExtra("img").toString())
            .into(binding.mealDetailesImg)


    }

}