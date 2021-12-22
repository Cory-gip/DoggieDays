package com.example.phase3project2practice.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.example.phase3project2practice.R
import com.example.phase3project2practice.databinding.FragmentSavedDogsBinding
import com.example.phase3project2practice.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

private lateinit var binding: FragmentSavedDogsBinding

@AndroidEntryPoint
class SavedDogsFragment : Fragment(R.layout.fragment_saved_dogs) {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSavedDogsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDogs().observe(viewLifecycleOwner, {
            binding.savedDogImage.load(it[0].dogImageURL)
        })
    }


}