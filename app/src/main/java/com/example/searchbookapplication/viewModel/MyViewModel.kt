package com.example.searchbookapplication.viewModel

import androidx.lifecycle.LiveData

import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel


class MyViewModel : ViewModel() {
    private val _myData = MutableLiveData<String>()
    val myData: LiveData<String>
        get() = _myData

    fun setMyData(data: String) {
        _myData.value = data
    }
}

