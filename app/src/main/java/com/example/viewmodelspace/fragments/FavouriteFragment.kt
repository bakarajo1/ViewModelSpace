package com.example.viewmodelspace.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.viewmodelspace.R
import com.example.viewmodelspace.adapter.ItemClick
import com.example.viewmodelspace.adapter.RecyclerAdapter
import com.example.viewmodelspace.viewmodel.SharedViewModel
import com.example.viewmodelspace.databinding.FragmentFavouriteBinding


class FavouriteFragment : Fragment(),ItemClick {
    private var _binding: FragmentFavouriteBinding?=null
    private val binding
        get() = _binding!!
    private val viewModel: SharedViewModel by activityViewModels()

    private val myAdapter by lazy { RecyclerAdapter(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding= FragmentFavouriteBinding.inflate(inflater,container,false)
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observers()
        binding.favoritesTextview.text=getString(R.string.fav_items)

    }

    private fun initRecyclerView() {
        with(binding.favoritesRecycler) {
            layoutManager = LinearLayoutManager(context)
            this.adapter = myAdapter
        }
    }
    private fun observers(){

       viewModel.user.observe(viewLifecycleOwner, Observer { binding.usernameTextView.text=it })
        viewModel.favoriteDishes.observe(viewLifecycleOwner, Observer { myAdapter.submitList(it)  })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    override fun onClick(position: Int) {

        Toast.makeText(context, "$position", Toast.LENGTH_SHORT).show()
        viewModel.changeFoodFavoriteState(position)
        myAdapter.ntf(position)
    }
}