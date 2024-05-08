package com.example.guessinggame

import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private val words = listOf("Android", "Activity", "Fragment")
    private val secretWord = words.random().uppercase()
    var secretWordDisplay = ""
    var correctGuesses = ""
    var incorrectGuesses = ""
    var livesLeft = 5

    init {
        secretWordDisplay = deriveSecretWordDisplay()
    }

    fun checkLetter(str: String) = if (correctGuesses.contains(str)) str else "_"

    fun deriveSecretWordDisplay(): String {
        var display = ""
        secretWord.forEach {
            display += checkLetter(it.toString())
        }
        return display
    }

    fun makeGuess(guess: String) {
        if (guess.length == 1) {
            if (secretWord.contains(guess)) {
                correctGuesses += guess
                secretWordDisplay = deriveSecretWordDisplay()
            } else {
                incorrectGuesses += "$guess "
                livesLeft--
            }
        }
    }

    fun isWon() = secretWord == secretWordDisplay

    fun isLost() = livesLeft <= 0

    fun resultMessage(): String {
        var message = if (isWon()) "You won!" else "You lost!"
        message += " The word was $secretWord"
        return message
    }
}