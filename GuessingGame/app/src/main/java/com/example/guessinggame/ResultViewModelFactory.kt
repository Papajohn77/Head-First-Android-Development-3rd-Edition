package com.example.guessinggame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ResultViewModelFactory(private val result: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (!modelClass.isAssignableFrom(ResultViewModel::class.java)) {
            throw IllegalArgumentException("Unknown ViewModel")
        }
        return ResultViewModel(result) as T
    }
}