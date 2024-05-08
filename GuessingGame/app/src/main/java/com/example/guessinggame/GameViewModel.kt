package com.example.guessinggame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private val words = listOf("Android", "Activity", "Fragment")
    private val secretWord = words.random().uppercase()
    private val _secretWordDisplay = MutableLiveData<String>()
    val secretWordDisplay: LiveData<String>
        get() = _secretWordDisplay
    private var correctGuesses = ""
    private val _incorrectGuesses = MutableLiveData<String>("")
    val incorrectGuesses: LiveData<String>
        get() = _incorrectGuesses
    private val _livesLeft = MutableLiveData<Int>(5)
    val livesLeft: LiveData<Int>
        get() = _livesLeft
    private val _gameOver = MutableLiveData<Boolean>(false)
    val gameOver: LiveData<Boolean>
        get() = _gameOver

    init {
        _secretWordDisplay.value = deriveSecretWordDisplay()
    }

    private fun checkLetter(str: String) = if (correctGuesses.contains(str)) str else "_"

    private fun deriveSecretWordDisplay(): String {
        var display = ""
        secretWord.forEach {
            display += checkLetter(it.toString())
        }
        return display
    }

    fun makeGuess(guess: String) {
        if (guess.length != 1) {
            return
        }

        if (secretWord.contains(guess)) {
            correctGuesses += guess
            _secretWordDisplay.value = deriveSecretWordDisplay()
        } else {
            _incorrectGuesses.value += "$guess "
            _livesLeft.value = livesLeft.value?.minus(1)
        }

        if (isWon() || isLost()) {
            _gameOver.value = true
        }
    }

    private fun isWon() = secretWord == secretWordDisplay.value

    private fun isLost() = (livesLeft.value ?: 0) <= 0

    fun resultMessage(): String {
        var message = if (isWon()) "You won!" else "You lost!"
        message += " The word was $secretWord"
        return message
    }
}