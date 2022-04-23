package com.hamzaaktay.adbootcampconnecttotheinternet.overview

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.hamzaaktay.adbootcampconnecttotheinternet.R
import com.hamzaaktay.adbootcampconnecttotheinternet.databinding.FragmentOverviewBinding

class OverviewFragment : Fragment() {

    private lateinit var binding : FragmentOverviewBinding

    private val viewModel : OverviewViewModel by lazy { ViewModelProvider(this) [OverviewViewModel::class.java]}

    private val characterAdapter by lazy { CharactersAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOverviewBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.rvCharacters.adapter = characterAdapter

        characterAdapter.onClick = {
            viewModel.displayCharacterDetail(it)
        }

        viewModel.navigateToSelectedCharacter.observe(viewLifecycleOwner) {
            if (it!=null) {
                this.findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(it))
                viewModel.displayCharacterDetailCompleted()
            }
        }

        setHasOptionsMenu(true) // --> Menu Fragmentta görünsün diye bu kod satırını ekledik.

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.filterCharacters(

            when (item.itemId) {
            R.id.show_gryffindor -> HarryPotterApiFilter.SHOW_GRYFFINDOR
            R.id.show_hufflepuff -> HarryPotterApiFilter.SHOW_HUFFLEPUFF
            R.id.show_ravenclaw -> HarryPotterApiFilter.SHOW_RAVENCLAW
            else -> HarryPotterApiFilter.SHOW_SLYTHERIN
        })
        return true
    }

}