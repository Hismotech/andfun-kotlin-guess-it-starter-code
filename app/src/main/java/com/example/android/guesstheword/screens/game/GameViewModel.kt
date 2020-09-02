package com.example.android.guesstheword.screens.game


import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private val timer: CountDownTimer // creating a timer field

    companion object {
        //This is created so we can call member of a class without creating instance of the class
        //Companion Objects are member of a class called without creating instances of the class
        //The objects represent different important time
        const val DONE = 0L//when game is over
        const val ONE_SECOND = 1000L //number of milliseconds in a second
        const val COUNTDOWN_TIME = 10000L // Total time of the game
    }

    private val _word = MutableLiveData<String>() //Setting up live data for word and internal version
    val word: LiveData<String> // external version encpasulation
        get() = _word // returns the internal mutablelIveDate has a Live Data using backing property to override

    // The current score
    private val _score = MutableLiveData<Int>() //Setting up live data for word and internal version
    val score: LiveData<Int>
        //external version encapsulation
        get() = _score

    private val _gameFinishedEvent = MutableLiveData<Boolean>()
    val gameFinished: LiveData<Boolean>
        get() = _gameFinishedEvent

    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime //encapsulate countdowntimer with live data


    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    init {
        _gameFinishedEvent.value = false
        resetList()
        nextWord()
        _score.value = 0
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {
            /**
             * Callback fired when the time is up.
             */
            override fun onFinish() {
                _currentTime.value = DONE
                _gameFinishedEvent.value = true
            }

            /**
             * Callback fired on regular interval.
             * @param millisUntilFinished The amount of time until finished.
             */
            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = (millisUntilFinished / ONE_SECOND)
            }
        }
        timer.start()
    }

    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }
    /**
     * Moves to the next word in the list
     */

    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
            resetList()
        }
        _word.value = wordList.removeAt(0)
    }

    /** Methods for buttons presses **/

    fun onSkip() {
        _score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = (score.value)?.plus(1)
        nextWord()
    }

    fun gameFinishComplete() {
        _gameFinishedEvent.value = false
    }

    override fun onCleared() {
        /*To avoid memory leaks, you should always cancel a CountDownTimer
        if you no longer need it. To do that, you can call:*/
        super.onCleared()
        timer.cancel()
    }

}