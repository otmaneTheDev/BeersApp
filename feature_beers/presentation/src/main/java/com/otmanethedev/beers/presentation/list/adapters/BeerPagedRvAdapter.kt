package com.otmanethedev.beers.presentation.list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.otmanethedev.beers.domain.models.Beer
import com.otmanethedev.beers.presentation.databinding.ItemBeerBinding
import com.otmanethedev.core.utils.loadFromUrl

class BeerPagedRvAdapter : PagingDataAdapter<Beer, BeerPagedRvAdapter.OompaLoompaViewHolder>(BEER_DIFF_CALLBACK) {

    var itemClickListener: (Beer) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OompaLoompaViewHolder {
        val binding = ItemBeerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OompaLoompaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OompaLoompaViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    companion object {
        private val BEER_DIFF_CALLBACK = object : DiffUtil.ItemCallback<Beer>() {
            override fun areItemsTheSame(oldItem: Beer, newItem: Beer): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Beer, newItem: Beer): Boolean = oldItem == newItem
        }
    }

    inner class OompaLoompaViewHolder(private val binding: ItemBeerBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(beer: Beer) {
            binding.img.loadFromUrl(beer.imageUrl)

            binding.txtName.text = beer.name
            binding.txtDescription.text = beer.description
            binding.txtABV.text = "${beer.abv}% ABV"

            binding.root.setOnClickListener {
                itemClickListener.invoke(beer)
            }
        }
    }
}
