package com.example.easycook.UI.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.easycook.Adapter.CategoryAdapter
import com.example.easycook.Adapter.MealsApater
import com.example.easycook.UI.activites.Meals
import com.example.easycook.ViewModel.CategoryFragmentVM
import com.example.easycook.databinding.FragmentCategoriBinding

class categori : Fragment() {

    private lateinit var binding: FragmentCategoriBinding
    private val vm: CategoryFragmentVM by viewModels()

    private lateinit var adapter: CategoryAdapter
    private lateinit var populer_adapter:MealsApater


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoriBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getCategoryList()
    }

    private fun getCategoryList() {
        vm.fetchCategory()

        vm.observeCategory().observe(viewLifecycleOwner) {
           details->
            adapter = CategoryAdapter {

                    val intent = Intent(activity, Meals::class.java)
                   intent.putExtra("name",it)
                    startActivity(intent)


            }

            adapter.setCategory(details)
            binding.recCategory.layoutManager =
                GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            //  binding.recCategory.layoutManager=LinearLayoutManager(context)
            binding.recCategory.adapter = adapter

            adapter.notifyDataSetChanged()

        }
    }
}