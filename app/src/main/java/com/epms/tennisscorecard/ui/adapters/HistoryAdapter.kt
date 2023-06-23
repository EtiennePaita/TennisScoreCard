package com.epms.tennisscorecard.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.epms.tennisscorecard.R
import com.epms.tennisscorecard.data.local.entities.Score
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
            viewHolder.itemBinding.player1Name.text = match.user.name
            viewHolder.itemBinding.historyPlayer1Score.text = getScore(match.userScore)
            viewHolder.itemBinding.player2Name.text = match.opponent.name
            viewHolder.itemBinding.historyPlayer2Score.text = getScore(match.opponentScore)
            println( "HISTORY A: " + match.userScore.toString())
            println( "HISTORY B: " + match.opponentScore.toString())


            viewHolder.itemBinding.itemHistoryContainer.setOnClickListener {
                listener.onMatchClick(match.matchId)
            }

            viewHolder.itemBinding.player1Name.setTextColor(
                viewHolder.itemBinding.player1Name.context.getColor(
                    if (match.isOver && match.winnerId == -1) R.color.green
                    else R.color.black
                )
            )
            viewHolder.itemBinding.player2Name.setTextColor(
                viewHolder.itemBinding.player2Name.context.getColor(
                    if (match.isOver && match.winnerId == match.opponent.id) R.color.green
                    else R.color.black
                )
            )


            if (!match.isOver) {
                //TODO : show icon or text "Match paused"
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

    private fun getScore(playerScore: Score): String {
        var score = ""
        playerScore.sets.forEach {
            score += "| ${it.gameScore} "
        }

        return "$score |"
    }

    class MatchHistoryViewHolder(val itemBinding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

}