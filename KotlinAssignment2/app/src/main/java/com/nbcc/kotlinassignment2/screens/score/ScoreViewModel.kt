package com.nbcc.kotlinassignment2.screens.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(scoreParam: Int, questionBankSizeParam: Int) : ViewModel() {
    // The final score
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    // The final score
    private val _questionBankSize = MutableLiveData<Int>()
    val questionBankSize: LiveData<Int>
        get() = _questionBankSize


    init {
        _score.value = scoreParam
        _questionBankSize.value = questionBankSizeParam
    }

}