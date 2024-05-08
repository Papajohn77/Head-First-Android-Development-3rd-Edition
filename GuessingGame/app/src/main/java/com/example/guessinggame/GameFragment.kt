package com.example.guessinggame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.guessinggame.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding
        get() = _binding!!

    private val words = listOf("Android", "Activity", "Fragment")
    private val secretWord = words.random().uppercase()
    private var secretWordDisplay = ""
    private var correctGuesses = ""
    private var incorrectGuesses = ""
    private var livesLeft = 5

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        val view = binding.root

        secretWordDisplay = deriveSecretWordDisplay()
        updateScreen()

        binding.guessBtn.setOnClickListener {
            makeGuess(binding.guess.text.toString().uppercase())
            binding.guess.text = null
            updateScreen()

            if (isWon() || isLost()) {
                val action = GameFragmentDirections.actionGameFragmentToResultFragment(resultMessage())
                view.findNavController().navigate(action)
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun updateScreen() {
        binding.word.text = secretWordDisplay
        binding.lives.text = "You have $livesLeft lives left."
        binding.incorrectGuesses.text = "Incorrect guesses: $incorrectGuesses"
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