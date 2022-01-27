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
import com.example.viewmodelspace.adapter.ItemClick
import com.example.viewmodelspace.adapter.RecyclerAdapter
import com.example.viewmodelspace.databinding.FragmentHomeBinding
import com.example.viewmodelspace.viewmodel.SharedViewModel


class HomerFragment : Fragment(),ItemClick {
    private var _binding: FragmentHomeBinding?=null
    private val binding
        get() = _binding!!
    private lateinit var adapterRec: RecyclerAdapter

    val viewModel: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding= FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterRec = RecyclerAdapter(this)
        binding.appNameTextView.text="SpaceViewModel"
        initRecyclerView()
        observers()
    }
    private fun observers(){

        viewModel.user.observe(viewLifecycleOwner, Observer { binding.usernameTextView.text=it })
        viewModel.defaultDishes.observe(viewLifecycleOwner, Observer { adapterRec.submitList(it)  })

    }

    private fun initRecyclerView() {
        with(binding.homeRecycler) {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapterRec
        }
        }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    override fun onClick(position: Int) {
        Toast.makeText(context, "$position", Toast.LENGTH_SHORT).show()
        viewModel.changeFoodFavoriteState(position)
        adapterRec.ntf(position)
    }


}