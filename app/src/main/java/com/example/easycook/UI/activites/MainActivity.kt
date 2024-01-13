package com.example.easycook.UI.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.easycook.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottNav =findViewById<BottomNavigationView>(R.id.bottom_navigation)

        val frag_controll=Navigation.findNavController(this,R.id.frag_host)

       NavigationUI.setupWithNavController(bottNav,frag_controll)
//dfkbmfdbdfb
    }
}