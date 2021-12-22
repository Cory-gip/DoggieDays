package com.example.phase3project2practice.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.phase3project2practice.R
import com.example.phase3project2practice.databinding.FragmentHomescreenBinding


private lateinit var binding: FragmentHomescreenBinding

class HomeFragment : Fragment(R.layout.fragment_homescreen) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomescreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.dogSearch.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_dogSearchFragment) }
        binding.savedDogsButton.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_savedDogsFragment) }
    }

}