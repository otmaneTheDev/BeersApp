package com.otmanethedev.beers.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.otmanethedev.beers.domain.models.Beer
import com.otmanethedev.beers.presentation.R
import com.otmanethedev.beers.presentation.databinding.FragmentBeerListBinding
import com.otmanethedev.beers.presentation.list.BeerListViewModel.BeerListUiState
import com.otmanethedev.beers.presentation.list.adapters.BeerRvAdapter
import com.otmanethedev.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BeerListFragment : BaseFragment<FragmentBeerListBinding>() {

    override val viewModel: BeerListViewModel by viewModels()
    private val beerRvAdapter by lazy { BeerRvAdapter() }

    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentBeerListBinding {
        return FragmentBeerListBinding.inflate(inflater, container, false)
    }

    override fun setUpUi() {
        super.setUpUi()
        setUpListeners()
        binding.rvBeers.adapter = beerRvAdapter
    }

    override fun setUpObservers() {
        super.setUpObservers()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        BeerListUiState.Idle -> Unit
                        is BeerListUiState.Error -> handleError()
                        BeerListUiState.Loading -> handleLoading(true)
                        is BeerListUiState.Success -> handleSuccess(it.beers)
                    }
                }
            }
        }
    }

    private fun setUpListeners() {
        beerRvAdapter.itemClickListener = {
            navigate(BeerListFragmentDirections.actionBeerListToBeerDetail(it))
        }
    }

    private fun handleLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.loading.visibility = View.VISIBLE
            binding.rvBeers.visibility = View.GONE
        } else {
            binding.loading.visibility = View.GONE
            binding.rvBeers.visibility = View.VISIBLE
        }
    }

    private fun handleSuccess(beers: List<Beer>) {
        beerRvAdapter.updateList(beers)
        handleLoading(false)
    }

    private fun handleError() {
        binding.txtError.text = getString(R.string.beers_list_error_msg)
        handleLoading(false)
    }
}