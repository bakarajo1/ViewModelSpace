package com.example.viewmodelspace.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.viewmodelspace.R
import com.example.viewmodelspace.viewmodel.SharedViewModel
import com.example.viewmodelspace.databinding.RegisterFragmentBinding

class RegisterFragment : Fragment() {
    private var _binding: RegisterFragmentBinding?=null
    private val binding
        get() = _binding!!
    val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= RegisterFragmentBinding.inflate(inflater,container,false)
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        binding.buttonLogIn.setOnClickListener {
            checkUsername()
        }
    }

    private fun checkUsername() {
       val temp=binding.editTextTextUserName.text.toString()
        val list= temp.toString().toCharArray()
        var includesDigit=false

        list.forEach {
            if(it.isDigit()){
                includesDigit=true
            }
        }
        if (temp.length<7){
            Toast.makeText(context, getString(R.string.usrname_size_restriction), Toast.LENGTH_SHORT).show()
        }else if (!includesDigit){
            Toast.makeText(context, getString(R.string.include_num_restriction), Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, getString(R.string.correct), Toast.LENGTH_SHORT).show()
            viewModel.setUser(temp.toString())
            findNavController().navigate(R.id.action_registerFragment_to_homerFragment)

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }


}