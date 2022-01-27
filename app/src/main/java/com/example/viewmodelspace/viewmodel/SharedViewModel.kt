package com.example.viewmodelspace.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.viewmodelspace.model.Food

class SharedViewModel : ViewModel() {

    private var favoriteFoodList= mutableListOf<Food>()

    private val _userName: MutableLiveData<String> = MutableLiveData()
    val user: LiveData<String> = _userName

    private val _defaultDishes: MutableLiveData<List<Food>> = MutableLiveData()
    val defaultDishes: LiveData<List<Food>> = _defaultDishes

    private val _favoriteDishes: MutableLiveData<List<Food>> = MutableLiveData()
    val favoriteDishes: LiveData<List<Food>> = _favoriteDishes

    init {
        _defaultDishes.value= foodList
    }


    fun setUser(name:String){
        _userName.value=name
    }


    fun changeFoodFavoriteState(position:Int){
        foodList[position].isFavorite = !foodList[position].isFavorite

      //  _defaultDishes.value= foodList

        if (!foodList[position].isFavorite){
            favoriteFoodList.remove(foodList[position])
            _favoriteDishes.value=favoriteFoodList



        }else{
            favoriteFoodList.add(foodList[position])
            _favoriteDishes.value=favoriteFoodList

        }





    }
companion object foods{
    private var foodList = mutableListOf<Food>(
        Food("Khinkali", false),
        Food("Qababi",false),
        Food("Khachapuri",false)
    )


}

}