package com.modernandroiddevelopment.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class MainViewModel: ViewModel() {
    private val listFruits = listOf("orange","banana","grape")
    val listResult = MutableStateFlow<List<String>>(emptyList())

    private val _eventToast = MutableSharedFlow<String>()
    val eventToast:SharedFlow<String> = _eventToast.asSharedFlow()

     suspend fun addFruit(fruit: String){
        if(listFruits.contains(fruit) && !listResult.value.contains(fruit)){
            val newList = listResult.value.toMutableList()
            newList.add(fruit)
            listResult.value = newList
        } else {
            _eventToast.emit("Você digitou $fruit e nâo tem na lista")
        }

    }
}