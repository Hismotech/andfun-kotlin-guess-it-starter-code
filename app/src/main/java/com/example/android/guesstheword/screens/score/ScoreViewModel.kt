package com.example.android.guesstheword.screens.score

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//In this exercise you'll pass data into a ViewModel.
// You'll create a view model factory that allows you to define a custom constructor for a ViewModel
// that gets called when you use ViewModelProviders
// implementation of the viewmodel factory instantiates the viewModel
//a factory is an object for creating other objects
// A factory is a method that returns object of varying class
//constructor is concrete in that it creates objects as instances of a single class
class ScoreViewModel(finalScore: Int) : ViewModel() {

    //Setting up live data for score and eventPlayAgain  internal version
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _eventPlayAgain = MutableLiveData<Boolean>()
    val eventPlayAgain: LiveData<Boolean>
        get() = _eventPlayAgain

    init {
        Log.i("ScoreViewModel", "Final Score: $finalScore")
        _score.value = finalScore
    }

    fun onPlayAgain() {
        _eventPlayAgain.value = true
    }

    fun onPlayAgainComplete() {
        _eventPlayAgain.value = false
    }
}