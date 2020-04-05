package com.example.project1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var questionCollection: List<Question>
    private var indexQuestion: Int = 0
    private lateinit var textViewQuestion: TextView
    private lateinit var buttonYes: Button
    private lateinit var buttonNo: Button
    private lateinit var buttonNext: ImageButton
    private lateinit var buttonPrevious: ImageButton
    private val tag: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i(tag, "onCreate() invoked")

        if(savedInstanceState != null) {
            indexQuestion = savedInstanceState.getInt(QUESTION_INDEX_KEY)
        }

        textViewQuestion = findViewById(R.id.textView)

        textViewQuestion.setOnClickListener {
            Toast.makeText(this, questionCollection[indexQuestion + 1].text, Toast.LENGTH_SHORT).show()
        }

        buttonYes = findViewById(R.id.buttonYes)
        buttonNo = findViewById(R.id.buttonNo)
        buttonNext = findViewById(R.id.buttonNext)
        buttonPrevious = findViewById(R.id.buttonPrevious)

        questionCollection = listOf(
                Question(resources.getString(R.string.question_1), resources.getBoolean(R.bool.answer_1)),
                Question(resources.getString(R.string.question_2), resources.getBoolean(R.bool.answer_2)),
                Question(resources.getString(R.string.question_3), resources.getBoolean(R.bool.answer_3)),
                Question(resources.getString(R.string.question_4), resources.getBoolean(R.bool.answer_4)),
                Question(resources.getString(R.string.question_5), resources.getBoolean(R.bool.answer_5)),
                Question(resources.getString(R.string.question_6), resources.getBoolean(R.bool.answer_6)),
                Question(resources.getString(R.string.question_7), resources.getBoolean(R.bool.answer_7)),
                Question(resources.getString(R.string.question_8), resources.getBoolean(R.bool.answer_8)),
                Question(resources.getString(R.string.question_9), resources.getBoolean(R.bool.answer_9)),
                Question(resources.getString(R.string.question_10), resources.getBoolean(R.bool.answer_10))
        )

        textViewQuestion.text = questionCollection[indexQuestion].text

        buttonYes.setOnClickListener {
            checkSnackbarYes(it)
            Log.d(tag, "We click button Yes!")
        }

        buttonNo.setOnClickListener {
            checkSnackbarNo(it)
            Log.d(tag, "We click button No!")
        }

        buttonNext.isEnabled = false

        buttonNext.setOnClickListener {
            checkButtonNext()
            Log.d(tag, "We click button Next!")
        }

        buttonPrevious.setOnClickListener {
            checkButtonPrevious()
            Log.d(tag, "We click button Previous!")
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(tag, "onStart() invoked")
    }

    override fun onResume() {
        super.onResume()
        Log.i(tag, "onResume() invoked")
    }

    override fun onPause() {
        super.onPause()
        Log.i(tag, "onPause() invoked")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(QUESTION_INDEX_KEY, indexQuestion)
    }

    companion object {
        private const val QUESTION_INDEX_KEY = "question_index_key"
    }

    private fun checkAnswerQuestion(myAnswer: Boolean, answerQuestionCollection: Boolean): Boolean {
        return  myAnswer == answerQuestionCollection
    }

    private fun checkButtonNext() {
        indexQuestion = (indexQuestion + 1) % questionCollection.size
        textViewQuestion.text = questionCollection[indexQuestion].text
        buttonNext.isEnabled = false
    }

    private fun checkButtonPrevious() {
        if(indexQuestion > 0)
            indexQuestion = (indexQuestion - 1) % questionCollection.size
        else
            questionCollection.lastIndex + 1
        textViewQuestion.text = questionCollection[indexQuestion].text
    }

    private fun checkSnackbarYes(it: View) {
        val snackbar: Snackbar? = null
        if(checkAnswerQuestion(false, questionCollection[indexQuestion].answerYes))
            Snackbar.make(it, "No Correct!", Snackbar.LENGTH_SHORT).setAction("Close") {
                snackbar?.dismiss()
            }.show()
        else
            Snackbar.make(it, "Correct!", Snackbar.LENGTH_SHORT).setAction("Close") {
                snackbar?.dismiss()
            }.show()
        buttonNext.isEnabled = true
    }

    private fun checkSnackbarNo(it: View) {
        val snackbar: Snackbar? = null
        if(checkAnswerQuestion(true, questionCollection[indexQuestion].answerYes))
            Snackbar.make(it, "No Correct!", Snackbar.LENGTH_SHORT).setAction("Close") {
                snackbar?.dismiss()
            }.show()
        else
            Snackbar.make(it, "Correct!", Snackbar.LENGTH_SHORT).setAction("Close") {
                snackbar?.dismiss()
            }.show()
        buttonNext.isEnabled = true
    }

    override fun onStop() {
        super.onStop()
        Log.i(tag, "onStop() invoked")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(tag, "onDestroy() invoked")
    }
}