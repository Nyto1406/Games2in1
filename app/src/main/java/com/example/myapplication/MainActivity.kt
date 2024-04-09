package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.BullsAndCowsGameScreen
import com.example.myapplication.HangmanGameScreen
import com.example.myapplication.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startHangmanGame(view: View) {
        val hangmanScreen = HangmanGameScreen()
        hangmanScreen.showNow(supportFragmentManager, "HangmanGameScreen")
    }

    fun startBullsAndCowsGame(view: View) {
        val bullsAndCowsScreen = BullsAndCowsGameScreen()
        bullsAndCowsScreen.showNow(supportFragmentManager, "BullsAndCowsGameScreen")
    }
}