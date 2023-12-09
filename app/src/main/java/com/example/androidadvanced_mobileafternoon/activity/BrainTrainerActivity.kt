package com.example.androidadvanced_mobileafternoon.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.example.androidadvanced_mobileafternoon.R
import com.example.androidadvanced_mobileafternoon.databinding.ActivityBrainTrainerBinding
import kotlin.random.Random

private lateinit var binding: ActivityBrainTrainerBinding
private lateinit var countDownTimer: CountDownTimer
private var timerDuration: Long = 30000
private var totalCorrectAnswers: Int = 0
private var totalWrongAnswers: Int = 0
private var totalQuestions: Int = 0

class BrainTrainerActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBrainTrainerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            startGame()
        }

        binding.btnOption1.setOnClickListener(this)
        binding.btnOption2.setOnClickListener(this)
        binding.btnOption3.setOnClickListener(this)
        binding.btnOption4.setOnClickListener(this)

    }

    private fun checkAnswer(selectedAnswer: Int) {
        val num1 = binding.tvQuestion.text.toString().substringBefore(" +").toInt()
        val num2 = binding.tvQuestion.text.toString().substringAfter("+ ").toInt()
        val correctAnswer = num1 + num2

        if (selectedAnswer == correctAnswer) {
            totalCorrectAnswers++
            binding.tvGameResult.text = "Correct!"
            binding.tvGameResult.setTextColor(Color.GREEN)
        } else {
            totalWrongAnswers++
            binding.tvGameResult.setTextColor(Color.RED)
            binding.tvGameResult.text = "Wrong!"
        }
        updateScore()
        nextQuestion()
    }

    private fun updateScore() {
        binding.tvSkor.text = "Correct: $totalCorrectAnswers\nWrong: $totalWrongAnswers"
    }

    private fun startGame() {
        totalCorrectAnswers = 0
        totalWrongAnswers = 0
        totalQuestions = 0
        binding.tvGameResult.text = ""
        binding.btnStart.visibility = View.INVISIBLE
        startTimer()
        nextQuestion()
    }

    private fun nextQuestion() {
        val num1 = Random.nextInt(21)
        val num2 = Random.nextInt(21)
        val correctAnswer = num1 + num2

        binding.tvQuestion.text = "$num1 + $num2"

        val correctAnswerPosition = Random.nextInt(4)

        val options = mutableListOf<Int>()
        for (i in 0 until 4) {
            if (i == correctAnswerPosition) {
                options.add(correctAnswer)
            } else {
                var wrongAnswer: Int
                do {
                    wrongAnswer = Random.nextInt(41)
                } while (wrongAnswer == correctAnswer)
                options.add(wrongAnswer)
            }
        }

        binding.btnOption1.text = options[0].toString()
        binding.btnOption2.text = options[1].toString()
        binding.btnOption3.text = options[2].toString()
        binding.btnOption4.text = options[3].toString()

        totalQuestions++
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(timerDuration, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                binding.tvTimer.text = "Timer: $seconds"
            }

            override fun onFinish() {
                binding.tvTimer.text = "Timer: 0s"
                showResult()
            }
        }
        countDownTimer.start()
    }

    private fun showResult() {
        binding.btnStart.setText("Start Again?")
        val accuracy = (totalCorrectAnswers.toDouble() / totalQuestions.toDouble()) * 100
        binding.tvGameResult.text =
            "Game Over\nScore: $totalCorrectAnswers/$totalQuestions\nAccuracy: ${
                "%.2f".format(accuracy)
            }%"
        binding.tvGameResult.setTextColor(Color.BLACK)
        binding.btnStart.visibility = View.VISIBLE
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_option_1 -> {
                checkAnswer(binding.btnOption1.text.toString().toInt())
            }

            R.id.btn_option_2 -> {
                checkAnswer(binding.btnOption2.text.toString().toInt())
            }

            R.id.btn_option_3 -> {
                checkAnswer(binding.btnOption3.text.toString().toInt())
            }

            R.id.btn_option_4 -> {
                checkAnswer(binding.btnOption4.text.toString().toInt())
            }
        }
    }

}