package com.otmanethedev.beers.presentation.list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.otmanethedev.beers.domain.models.Beer
import com.otmanethedev.beers.presentation.databinding.ItemBeerBinding
import com.otmanethedev.core.utils.loadFromUrl

class BeerRvAdapter : RecyclerView.Adapter<BeerRvAdapter.BeerViewHolder>() {

    var items = listOf<Beer>()
        private set

    var itemClickListener: (Beer) -> Unit = {}

    fun updateList(items: List<Beer>) {
        val diffCallback = BeerDiffCallback(this.items, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)

        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val binding = ItemBeerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BeerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class BeerViewHolder(private val binding: ItemBeerBinding) : RecyclerView.ViewHolder(binding.root) {
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

    class BeerDiffCallback(private val oldList: List<Beer>, private val newList: List<Beer>) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }

}
