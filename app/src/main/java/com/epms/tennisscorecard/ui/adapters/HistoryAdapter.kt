package com.epms.tennisscorecard.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.epms.tennisscorecard.databinding.ItemHistoryBinding
import com.epms.tennisscorecard.domain.models.MatchRecap


class HistoryAdapter(
    private val listener: MatchHistoryInterface
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var matches: List<MatchRecap?>? = null

    interface MatchHistoryInterface {
        fun onMatchClick(matchId: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemBinding =
            ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchHistoryViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder: MatchHistoryViewHolder = holder as MatchHistoryViewHolder
        matches!![viewHolder.adapterPosition]?.let { match ->
            // TODO :
            //viewHolder.itemBinding.player2Name.text = match.user.name
            viewHolder.itemBinding.player2Name.text = match.opponent.name
            viewHolder.itemBinding.itemHistoryContainer.setOnClickListener {
                listener.onMatchClick(match.matchId)
            }
        }
    }

    override fun getItemCount(): Int {
        return matches?.size ?: 0
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(entities: List<MatchRecap?>?) {
        matches = entities
        notifyDataSetChanged()
    }

    class MatchHistoryViewHolder(val itemBinding: ItemHistoryBinding): RecyclerView.ViewHolder(itemBinding.root)

}