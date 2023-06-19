package com.epms.tennisscorecard.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.epms.tennisscorecard.databinding.ItemPlayerBinding
import com.epms.tennisscorecard.models.PlayerEntity


class PlayersAdapter(
    private val listener: PlayersInterface
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var players: List<PlayerEntity?>? = null

    interface PlayersInterface {
        fun onPlayerClick(eventEntity: PlayerEntity)
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
            viewHolder.itemBinding.playerNameText.setOnClickListener {
                listener.onPlayerClick(player)
            }
        }
    }

    override fun getItemCount(): Int {
        return players?.size ?: 0
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(entities: List<PlayerEntity?>?) {
        players = entities
        notifyDataSetChanged()
    }

    class PlayerViewHolder(val itemBinding: ItemPlayerBinding): RecyclerView.ViewHolder(itemBinding.root)

}