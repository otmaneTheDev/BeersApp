package com.otmanethedev.beers.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.otmanethedev.beers.presentation.R
import com.otmanethedev.beers.presentation.databinding.FragmentBeerListBinding
import com.otmanethedev.beers.presentation.list.BeerListViewModel.BeerListAction
import com.otmanethedev.beers.presentation.list.adapters.BeerPagedRvAdapter
import com.otmanethedev.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BeerListFragment : BaseFragment<FragmentBeerListBinding>() {

    override val viewModel: BeerListViewModel by viewModels()
    private val beerRvAdapter by lazy { BeerPagedRvAdapter() }

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
                viewModel.beers.collect {
                    beerRvAdapter.submitData(it)
                }
            }
        }
    }

    private fun setUpListeners() {
        beerRvAdapter.itemClickListener = {
            navigate(BeerListFragmentDirections.actionBeerListToBeerDetail(it))
        }
        beerRvAdapter.addLoadStateListener {
            binding.rvBeers.isVisible = it.source.refresh is LoadState.NotLoading
            binding.loading.isVisible = it.source.refresh is LoadState.Loading

            handleError(it)
        }

        binding.inputSearch.doAfterTextChanged { searchTxt ->
            viewModel.handleAction(BeerListAction.FilterByName(searchTxt.toString()))
        }
    }

    private fun handleError(loadState: CombinedLoadStates) {
        val error = loadState.source.append as? LoadState.Error ?: loadState.source.append as? LoadState.Error

        error?.let {
            Toast.makeText(context, R.string.beers_list_error_msg, Toast.LENGTH_SHORT).show()
        }
    }
}