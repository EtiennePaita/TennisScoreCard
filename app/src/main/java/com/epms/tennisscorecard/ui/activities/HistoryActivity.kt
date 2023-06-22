package com.epms.tennisscorecard.ui.activities

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.epms.tennisscorecard.R
import com.epms.tennisscorecard.databinding.ActivityHistoryBinding
import com.epms.tennisscorecard.ui.adapters.HistoryAdapter
import com.epms.tennisscorecard.ui.viewModels.HistoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryActivity: BaseActivity(), HistoryAdapter.MatchHistoryInterface {
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var historyAdapter: HistoryAdapter
    private val historyViewModel: HistoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setUIListener()
        setRecyclerView()
        setObservers()

        historyViewModel.getAllMatches()
    }

    private fun setupUI() {
        binding.header.headerTitle.text = getString(R.string.history_title)
        binding.noHistoryTextView.text = getString(R.string.history_not_found_error)
    }

    private fun setUIListener() {
        binding.header.headerCtaButton.setOnClickListener { finish() }
    }

    private fun setRecyclerView() {
        this.historyAdapter = HistoryAdapter(this)
        binding.historyRecyclerview.adapter = this.historyAdapter
        binding.historyRecyclerview.setHasFixedSize(true)
    }

    private fun setObservers() {
        historyViewModel.matches.observe(this) { history ->
            historyAdapter.setData(history)
            history?.let {
                if(it.isEmpty()) showEmptyView() else hideEmptyView()
            } ?: run {
                showEmptyView()
            }
        }
    }

    private fun showEmptyView() {
        binding.noHistoryTextView.visibility = View.VISIBLE
        binding.historyRecyclerview.visibility = View.GONE
    }

    private fun hideEmptyView() {
        binding.historyRecyclerview.visibility = View.VISIBLE
        binding.noHistoryTextView.visibility = View.GONE
    }

    override fun onMatchClick(matchId: Int) {
        //TODO : open recap Activity
    }
}