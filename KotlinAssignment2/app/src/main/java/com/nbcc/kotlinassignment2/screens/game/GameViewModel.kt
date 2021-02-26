package com.nbcc.kotlinassignment2.screens.game

import android.content.Context
import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nbcc.kotlinassignment2.R

data class Question (
    val questionID: Int,
    val answer: Boolean,
    var attempted: Boolean = false,
    var answered: Boolean = false

)

class GameViewModel : ViewModel(){

    private var questionIndex = 0;
    private lateinit var questionBank: MutableList<Question>

    // The current score
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    // The current question
    private val _question = MutableLiveData<Int>()
    val question: LiveData<Int>
        get() = _question

    // The current question
    private val _questionText = MutableLiveData<String>()
    val questionText: LiveData<String>
        get() = _questionText

    // Attempted
    private val _attempted = MutableLiveData<Boolean>()
    val attempted: LiveData<Boolean>
        get() = _attempted


    // checkbox True is enabled
    private val _checkTrue = MutableLiveData<Boolean>()
    val checkTrue: LiveData<Boolean>
        get() = _checkTrue


    // checkbox False is enabled
    private val _checkFalse = MutableLiveData<Boolean>()
    val checkFalse: LiveData<Boolean>
        get() = _checkFalse

    // question is correct
    private val _questionIsCorrect = MutableLiveData<Boolean>()
    val questionIsCorrect: LiveData<Boolean>
        get() = _questionIsCorrect


    // games has ended
    private val _gameEnded = MutableLiveData<Boolean>()
    val gameEnded: LiveData<Boolean>
        get() = _gameEnded


    val questionBankSize : Int
        get() = questionBank.size

    init {
        Log.i("GameViewModel", "GameViewModel created!")
        startNewGame()
    }

    fun startNewGame(){
        questionIndex = 0
        _gameEnded.value = false

        //Reset and shuffle questions
        resetQuestions()

        updateQuestion()

    }

    fun questionsAttempted(): Int{
        return questionBank.count{it.attempted }
    }

    /** Method for the game completed event **/
    fun onGameFinish() {
        _gameEnded.value = true
    }

    fun onGameFinishComplete() {
        _gameEnded.value = false
    }

    fun onAnswerClick(answer: Boolean){
        questionBank[questionIndex].attempted = true
        questionBank[questionIndex].answered = answer

        updateQuestion()
    }

    fun moveQuestion(increment: Int){

        if(increment == 1 && questionIndex == (questionBank.size - 1)){
            questionIndex = 0
        }else if(increment == -1 && questionIndex == 0){
            questionIndex = questionBank.size - 1
        }else{
            questionIndex = questionIndex + increment
        }

        updateQuestion()
    }


    fun updateQuestion(){
        _question.value = questionBank.elementAt(questionIndex).questionID
        //_questionText.value = Resources.getSystem().getString(questionBank.elementAt(questionIndex).questionID)
        _attempted.value =  questionBank.elementAt(questionIndex).attempted
        _questionIsCorrect.value =  questionBank.elementAt(questionIndex).answer == questionBank.elementAt(questionIndex).answered

        _checkFalse.value = questionBank.elementAt(questionIndex).attempted
                            && !questionBank.elementAt(questionIndex).answer
                            && questionBank.elementAt(questionIndex).answer == questionBank.elementAt(questionIndex).answered
        _checkTrue.value = questionBank.elementAt(questionIndex).attempted
                && questionBank.elementAt(questionIndex).answer
                && questionBank.elementAt(questionIndex).answer == questionBank.elementAt(questionIndex).answered

        _score.value = questionBank.count{it.attempted && it.answer == it.answered}

        if(questionsAttempted() == questionBank.size)
            onGameFinish()
    }

    private fun resetQuestions(){
        Log.i("GameViewModel", "resetQuestions")
        questionBank = mutableListOf(
            Question(R.string.question_1, false),
            Question(R.string.question_2, true),
            Question(R.string.question_3, true),
            Question(R.string.question_4, false),
            Question(R.string.question_5, false),
            Question(R.string.question_6, true),
            Question(R.string.question_7, false),
            Question(R.string.question_8, true),
            Question(R.string.question_9, false),
            Question(R.string.question_10, false),
            Question(R.string.question_11, false),
            Question(R.string.question_12, true),
            Question(R.string.question_13, false),
            Question(R.string.question_14, true),
            Question(R.string.question_15, false),
            Question(R.string.question_16, false),
            Question(R.string.question_17, true),
            Question(R.string.question_18, false),
            Question(R.string.question_19, false),
            Question(R.string.question_20, true)
        )

        Log.i("GameViewModel", "resetQuestions2")
        questionBank.shuffled()
    }
}