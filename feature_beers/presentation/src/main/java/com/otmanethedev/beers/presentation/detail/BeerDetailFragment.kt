package com.otmanethedev.beers.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.otmanethedev.beers.presentation.databinding.FragmentBeerDetailBinding
import com.otmanethedev.core.base.BaseFragment
import com.otmanethedev.core.utils.loadFromUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeerDetailFragment : BaseFragment<FragmentBeerDetailBinding>() {

    private val beer by lazy {
        val args by navArgs<BeerDetailFragmentArgs>()
        args.beer
    }

    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentBeerDetailBinding {
        return FragmentBeerDetailBinding.inflate(inflater, container, false)
    }

    override fun setUpUi() {
        super.setUpUi()

        binding.txtName.text = beer.name
        binding.img.loadFromUrl(beer.imageUrl)
        binding.txtDescription.text = beer.description

        binding.specificationABV.value = "${beer.abv}%"
        binding.specificationPh.value = beer.ph.toString()
        binding.specificationDate.value = beer.date

        binding.txtMalts.text = beer.malts.joinToString(", ")
        binding.txtHops.text = beer.hops.joinToString(", ")

        setUpListeners()
    }

    private fun setUpListeners() {
        binding.btnBack.setOnClickListener {
            navigateUp()
        }
    }
}