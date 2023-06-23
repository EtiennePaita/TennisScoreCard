package com.epms.tennisscorecard.ui.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.epms.tennisscorecard.R
import com.epms.tennisscorecard.databinding.ItemPlayerBinding
import com.epms.tennisscorecard.domain.models.Player


class PlayersAdapter(
    private val listener: PlayersInterface
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var players: List<Player?>? = null

    interface PlayersInterface {
        fun onPlayerClick(eventEntity: Player)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemBinding =
            ItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayerViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder: PlayerViewHolder = holder as PlayerViewHolder
        players!![viewHolder.adapterPosition]?.let { player ->
            viewHolder.itemBinding.playerNameText.text = player.name
            if (viewHolder.adapterPosition % 2 == 0) {
                viewHolder.itemBinding.playerRow.setBackgroundColor(
                    viewHolder.itemBinding.playerRow.context.getColor(R.color.white))
            } else {
                viewHolder.itemBinding.playerRow.setBackgroundColor(viewHolder.itemBinding.playerRow.context.getColor(R.color.row))
            }
            viewHolder.itemBinding.playerNameText.setOnClickListener {
                listener.onPlayerClick(player)
            }
        }
    }

    override fun getItemCount(): Int {
        return players?.size ?: 0
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(entities: List<Player?>?) {
        players = entities
        notifyDataSetChanged()
    }

    class PlayerViewHolder(val itemBinding: ItemPlayerBinding): RecyclerView.ViewHolder(itemBinding.root)

}