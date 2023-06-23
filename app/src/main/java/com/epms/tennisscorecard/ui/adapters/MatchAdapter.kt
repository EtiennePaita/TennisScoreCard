package com.epms.tennisscorecard.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.epms.tennisscorecard.databinding.ItemMatchSetBinding
import com.epms.tennisscorecard.domain.models.MatchState


class MatchAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var matches: MatchState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemBinding =
            ItemMatchSetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchSetViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder: MatchSetViewHolder = holder as MatchSetViewHolder
        matches?.let { match ->
            val player1Sets = match.player1State.getSets()
            val player2Sets = match.player2State.getSets()

            viewHolder.itemBinding.setIndicator.text = "Set ${viewHolder.adapterPosition + 1}"
            viewHolder.itemBinding.player1ScoreSet.text =
                player1Sets[viewHolder.adapterPosition].gameScore.toString()
            viewHolder.itemBinding.player2ScoreSet.text =
                player2Sets[viewHolder.adapterPosition].gameScore.toString()
        }
    }

    override fun getItemCount(): Int {
        return matches?.player1State?.getSets()?.size ?: 0
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(matchState: MatchState?) {
        matches = matchState
        notifyDataSetChanged()
    }

    class MatchSetViewHolder(val itemBinding: ItemMatchSetBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

}