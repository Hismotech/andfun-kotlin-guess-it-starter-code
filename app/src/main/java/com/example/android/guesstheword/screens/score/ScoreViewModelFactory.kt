package com.example.android.guesstheword.screens.score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//a factory is an object for creating other objects
// A factory is a method that returns object of varying class
class ScoreViewModelFactory(private val finalScore: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        //create method purpose to create and return ur view  model
        if (modelClass.isAssignableFrom(ScoreViewModel::class.java)) {
            // TODO Construct and return the ScoreViewModel
            return ScoreViewModel(finalScore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
