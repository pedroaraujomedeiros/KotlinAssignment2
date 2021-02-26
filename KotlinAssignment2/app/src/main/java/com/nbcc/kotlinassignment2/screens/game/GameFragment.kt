package com.nbcc.kotlinassignment2.screens.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment



import com.nbcc.kotlinassignment2.R
import com.nbcc.kotlinassignment2.databinding.FragmentGameBinding


class GameFragment : Fragment() {

    private lateinit var gameViewModel: GameViewModel
    private lateinit var binding: FragmentGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)


        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_game,container,false)


        binding.game = gameViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // Observer for the Game finished event
        gameViewModel.gameEnded.observe(viewLifecycleOwner, Observer<Boolean> { hasFinished ->
            if (hasFinished) gameFinished()
        })

        // Inflate the layout for this fragment
        return binding.root
    }

    /**
     * Called when the game is finished
     */
    private fun gameFinished() {
        Toast.makeText(activity, "Game has just finished", Toast.LENGTH_SHORT).show()


        val action = GameFragmentDirections.actionGameFragmentToScoreFragment(gameViewModel.score.value?:0,gameViewModel.questionBankSize)
        NavHostFragment.findNavController(this).navigate(action)

        gameViewModel.onGameFinishComplete()
    }
}