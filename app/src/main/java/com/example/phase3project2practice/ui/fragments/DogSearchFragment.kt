package com.example.phase3project2practice.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.example.phase3project2practice.databinding.FragmentDogSearchBinding
import com.example.phase3project2practice.network.Dog
import com.example.phase3project2practice.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

private lateinit var binding: FragmentDogSearchBinding

private var index: Int = 0

@AndroidEntryPoint
class DogSearchFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    private var dogList = mutableListOf<Dog>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDogSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveDogButton.setOnClickListener { viewModel.saveDog() }
        binding.dogButton.setOnClickListener { viewModel.getRandomDog() }
        binding.nextDogButton.setOnClickListener { viewModel.displayNextDogInHistory() }
        binding.prevDogButton.setOnClickListener { viewModel.displayPreviousDogInHistory() }

        viewModel.searchHistory.observe(viewLifecycleOwner, {
            dogList = it
        })

        viewModel.currentlyDisplayedDogIndex.observe(viewLifecycleOwner, {
            index = it
            binding.dogImage.load(dogList[index].message)
        })


    }


}