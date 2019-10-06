package com.buildreams.scrumpoker.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.buildreams.scrumpoker.BR
import com.buildreams.scrumpoker.R
import com.buildreams.scrumpoker.domain.entity.Card

import javax.inject.Inject

class DeploymentCardAdapter @Inject
constructor() : RecyclerView.Adapter<DeploymentCardAdapter.CardViewHolder>() {

    private lateinit var cards: List<Card>

    fun setCards(_cards: List<Card>) {
        this.cards = _cards
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.item_card,
            parent, false
        )

        return CardViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = cards[position]
        holder.bind(item)
    }

    class CardViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(card: Card) {
            binding.apply {
                setVariable(BR.card, card)
                executePendingBindings()
            }
        }
    }
}
