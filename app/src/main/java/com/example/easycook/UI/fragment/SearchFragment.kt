package com.example.easycook.UI.fragment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.easycook.Adapter.CategoryAdapter
import com.example.easycook.Adapter.MealsApater
import com.example.easycook.R
import com.example.easycook.UI.activites.MealDetailes
import com.example.easycook.ViewModel.HomeFragmentVM
import com.example.easycook.ViewModel.SearchFragmentVM
import com.example.easycook.databinding.FragmentHomeBinding
import com.example.easycook.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val vm: SearchFragmentVM by viewModels()
    private lateinit var adapter: MealsApater
    private val vm2: HomeFragmentVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//
        binding.backArrow.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_home)
        }

        binding.edSearch.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(
                charSequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }


            override fun onTextChanged(
                charSequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                vm.fetchSearchMeal(binding.edSearch.text.toString())

                vm.observeSearchMeal().observe(viewLifecycleOwner) {

                    adapter = MealsApater {

                        clikeMeal(it)
                    }
                    adapter.setPopulerMeals(it)
                    binding.rec.layoutManager =
                        GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
                    binding.rec.adapter = adapter

                    adapter.notifyDataSetChanged()

                }
            }


            override fun afterTextChanged(editable: Editable?) {}


        })


    }

    private fun clikeMeal(id: Int) {
        vm2.fetchPopularMealDetails(id)

        vm2.observePopularMealDetails().observe(this) {

            val intent = Intent(requireContext(), MealDetailes::class.java)
            intent.putExtra("name", it.strMeal)
            intent.putExtra("img", it.strMealThumb)
            intent.putExtra("youtube", it.strYoutube)
            intent.putExtra("intro", it.strInstructions)
            intent.putExtra("area", it.strArea)
            intent.putExtra("category", it.strCategory)
            startActivity(intent)
        }
    }
}