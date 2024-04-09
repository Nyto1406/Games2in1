package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import java.util.Locale
import kotlin.random.Random

class HangmanGameScreen : DialogFragment() {
    private lateinit var wordInput: EditText
    private lateinit var wordDisplay: TextView
    private lateinit var hangmanImage: ImageView
    private lateinit var letterButtons: MutableList<Button>
    private var answer: String? = null
    private var maskedWord: String? = null
    private var wrongGuesses = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.hangman_game_screen, container, false)
        wordInput = view.findViewById(R.id.word_input)
        val startButton = view.findViewById<Button>(R.id.start_button)
        wordDisplay = view.findViewById(R.id.word_display)
        hangmanImage = view.findViewById(R.id.hangman_image)
        letterButtons = ('a'..'z').map { char ->
            view.findViewById<Button>(resources.getIdentifier("letter_$char", "id", context?.packageName))
        }.toMutableList()

        startButton.setOnClickListener { startGame() }
        return view
    }

    private fun startGame() {
        val inputWord = wordInput.text.toString().trim()
        answer = if (inputWord.length in MIN_WORD_LENGTH..MAX_WORD_LENGTH) {
            inputWord.uppercase(Locale.getDefault())
        } else {
            getRandomWordFromFile(WORDS_FILE).uppercase(Locale.getDefault())
        }

        maskedWord = "_".repeat(answer?.length ?: 0)
        wordDisplay.text = maskedWord
        wrongGuesses = 0
        hangmanImage.setImageResource(R.drawable.hangman_0)
        enableLetterButtons(true)
    }

    fun IntRange.randomOrNull(): Int? {
        return if (isEmpty()) null else Random.Default.nextInt(first, last + 1)
    }

    private fun enableLetterButtons(enable: Boolean) {
        letterButtons.forEach { button ->
            button.isEnabled = enable
            button.setOnClickListener { handleGuess(button.text.toString()) }
        }
    }

    private fun handleGuess(letter: String) {
        var correctGuess = false
        val wordBuilder = StringBuilder(maskedWord)
        for (i in answer?.indices ?: emptyList()) {
            if (answer?.get(i) == letter[0]) {
                wordBuilder.setCharAt(i, letter[0])
                correctGuess = true
            }
        }
        maskedWord = wordBuilder.toString()

        if (correctGuess) {
            wordDisplay.text = maskedWord
            if (maskedWord == answer) {
                wordDisplay.setText(getString(R.string.game_won))
                enableLetterButtons(false)
            }
        } else {
            wrongGuesses++
            hangmanImage.setImageResource(resources.getIdentifier("hangman_$wrongGuesses", "drawable", context?.packageName))
            if (wrongGuesses == 6) {
                wordDisplay.setText(getString(R.string.game_lost))
                enableLetterButtons(false)
            }
        }
    }

    private fun getRandomWordFromFile(fileName: String): String {
        return context?.assets?.open(fileName)?.bufferedReader()?.useLines { lines ->
            lines.filter { it.length in MIN_WORD_LENGTH..MAX_WORD_LENGTH }
                .map { it.uppercase(Locale.getDefault()) }
                .toList()
                .randomOrNull()
                ?: "DEFAULT"
        } ?: "DEFAULT"
    }

    companion object {
        private const val WORDS_FILE = "words.txt"
        private const val MIN_WORD_LENGTH = 4
        private const val MAX_WORD_LENGTH = 12
    }
}
