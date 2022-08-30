package com.cj3dreams.majorpay.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cj3dreams.majorpay.R
import com.cj3dreams.majorpay.view.adapter.HistoryAdapter

class HistoryFragment : Fragment(), View.OnClickListener {
    private lateinit var historyRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_history, container, false)

        historyRecyclerView = view.findViewById(R.id.historyRW)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        historyRecyclerView.layoutManager =
            LinearLayoutManager(requireContext())
        historyRecyclerView.adapter = HistoryAdapter(requireContext(), emptyList(), this)
    }

    override fun onClick(v: View?) {

    }
}