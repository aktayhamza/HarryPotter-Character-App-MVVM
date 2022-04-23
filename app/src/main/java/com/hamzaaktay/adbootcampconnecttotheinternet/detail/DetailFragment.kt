package com.hamzaaktay.adbootcampconnecttotheinternet.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.hamzaaktay.adbootcampconnecttotheinternet.R
import com.hamzaaktay.adbootcampconnecttotheinternet.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        val selectedCharacter = DetailFragmentArgs.fromBundle(requireArguments()).selectedCharacter
        val viewModelFactory = DetailViewModelFactory(selectedCharacter,requireActivity().application)

        binding.viewModel = ViewModelProvider(this,viewModelFactory) [DetailViewModel::class.java]
    }

}