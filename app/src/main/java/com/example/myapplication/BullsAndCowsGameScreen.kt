package com.example.myapplication
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import kotlin.random.Random

class BullsAndCowsGameScreen : DialogFragment() {
    private lateinit var numberInput: EditText
    private lateinit var numberDisplay: TextView
    private lateinit var digitButtons: MutableList<Button>
    private var answer: Int? = null
    private var bulls = 0
    private var cows = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.bulls_and_cows_game_screen, container, false)
        numberInput = view.findViewById(R.id.number_input)
        val startButton = view.findViewById<Button>(R.id.start_button)
        numberDisplay = view.findViewById(R.id.number_display)
        digitButtons = mutableListOf(
            view.findViewById(R.id.digit_0),
            view.findViewById(R.id.digit_1),
            view.findViewById(R.id.digit_2),
            view.findViewById(R.id.digit_3),
            view.findViewById(R.id.digit_4),
            view.findViewById(R.id.digit_5),
            view.findViewById(R.id.digit_6),
            view.findViewById(R.id.digit_7),
            view.findViewById(R.id.digit_8),
            view.findViewById(R.id.digit_9)
        )
        startButton.setOnClickListener { startGame() }
        return view
    }

    private fun startGame() {
        val inputNumber = numberInput.text.toString().trim()
        answer = if (inputNumber.length in MIN_NUMBER_LENGTH..MAX_NUMBER_LENGTH && isUniqueDigits(inputNumber)) {
            inputNumber.toInt()
        } else {
            generateRandomNumber()
        }
        numberDisplay.text = ""
        enableDigitButtons(true)
    }

    private fun isUniqueDigits(number: String): Boolean {
        val visited = BooleanArray(10)
        number.forEachIndexed { index, char ->
            val digit = char.code - '0'.code
            if (visited[digit]) {
                return false
            }
            visited[digit] = true
        }
        return true
    }

    private fun generateRandomNumber(): Int {
        val length = Random.nextInt(MIN_NUMBER_LENGTH, MAX_NUMBER_LENGTH + 1)
        val digits = MutableList(10) { it }
        digits.shuffle()
        val sb = StringBuilder()
        for (i in 0 until length) {
            sb.append(digits[i])
        }
        return sb.toString().toInt()
    }

    private fun enableDigitButtons(enable: Boolean) {
        digitButtons.forEach { button ->
            button.isEnabled = enable
            button.setOnClickListener { handleGuess(button.text.toString()) }
        }
    }

    private fun handleGuess(guess: String) {
        if (guess.length != (answer?.toString()?.length ?: 0)) return

        var bulls = 0
        var cows = 0
        guess.forEachIndexed { index, char ->
            if (char == answer.toString()[index]) {
                bulls++
            } else if (answer.toString().contains(char)) {
                cows++
            }
        }

        numberDisplay.text = resources.getString(R.string.game_result, guess, bulls, cows)

        if (bulls == answer?.toString()?.length) {
            numberDisplay.text = resources.getString(R.string.game_won)
            enableDigitButtons(false)
        }
    }

    companion object {
        const val MIN_NUMBER_LENGTH = 3
        const val MAX_NUMBER_LENGTH = 6
    }
}