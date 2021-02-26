package com.nbcc.kotlinassignment2.screens.score

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.nbcc.kotlinassignment2.R
import com.nbcc.kotlinassignment2.databinding.FragmentScoreBinding


class ScoreFragment : Fragment() {

    private lateinit var viewModel: ScoreViewModel
    private lateinit var viewModelFactory: ScoreViewModelFactory
    private lateinit var binding: FragmentScoreBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_score, container, false)
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_score,container,false)

        val a = ScoreFragmentArgs.fromBundle(arguments!!);
        viewModelFactory = ScoreViewModelFactory(a.scoreParam, a.questionBankSizeParam)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ScoreViewModel::class.java)


        binding.score = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

}