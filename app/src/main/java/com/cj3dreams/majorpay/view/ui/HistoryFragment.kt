package com.cj3dreams.majorpay.view.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cj3dreams.majorpay.MainActivity
import com.cj3dreams.majorpay.R
import com.cj3dreams.majorpay.model.history.Result
import com.cj3dreams.majorpay.view.adapter.HistoryAdapter
import com.cj3dreams.majorpay.vm.HistoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : Fragment(), View.OnClickListener {
    private lateinit var historyRecyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var historyDetailRoundedBottomSheetDialogFragment: HistoryDetailRoundedBottomSheetDialogFragment
    private val historyViewModel: HistoryViewModel by viewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        historyViewModel.getAllHistory()
        historyViewModel.getAllHistoryFromLocal()
        (activity as MainActivity).apply {
            topBar.visibility = View.VISIBLE
            nameOfFrgTx.text = "История"
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_history, container, false)

        historyRecyclerView = view.findViewById(R.id.historyRW)
        progressBar = view.findViewById(R.id.historyProgressBar)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        historyRecyclerView.layoutManager =
            LinearLayoutManager(requireContext())

        historyViewModel.liveDbHistory.observe(viewLifecycleOwner, {
            if (!it.isNullOrEmpty()) progressBar.visibility = View.GONE
            historyRecyclerView.adapter = HistoryAdapter(requireContext(), it, this, false)
        })

    }


    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.rootItemH ->{
                val tag = v.tag as Result
                historyDetailRoundedBottomSheetDialogFragment = HistoryDetailRoundedBottomSheetDialogFragment.instanceHistoryDetailFragment(tag)
                historyDetailRoundedBottomSheetDialogFragment.show(requireActivity().supportFragmentManager, "Detail")
            }
        }
    }
}