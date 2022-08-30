package com.cj3dreams.majorpay.view.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.cj3dreams.majorpay.R
import com.cj3dreams.majorpay.view.adapter.AccountsAdapter
import com.cj3dreams.majorpay.view.adapter.HistoryAdapter
import com.cj3dreams.majorpay.vm.HomeViewModel
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager
import com.rd.PageIndicatorView
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), View.OnClickListener {
    private lateinit var horizontalInfiniteCycleViewPager: HorizontalInfiniteCycleViewPager
    private lateinit var dotsIndicator: PageIndicatorView
    private lateinit var historyRecyclerView: RecyclerView
    private val homeViewModel: HomeViewModel by viewModel()

    private var previousPage = 0
    private var currentPage = -1
    private var mSize = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        homeViewModel.getAllCards()
        homeViewModel.getAllHistory()
        homeViewModel.getAllCardsFromLocal()
        homeViewModel.getAllHistoryFromLocal()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view =  inflater.inflate(R.layout.fragment_home, container, false)

        horizontalInfiniteCycleViewPager = view.findViewById(R.id.homeHorizonInfCycleViewPager)
        dotsIndicator = view.findViewById(R.id.homeDotsIndicator)
        historyRecyclerView = view.findViewById(R.id.homeHistoryRW)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.liveDbCard.observe(viewLifecycleOwner, { db ->
            horizontalInfiniteCycleViewPager.adapter =
                AccountsAdapter(db, requireContext(), this)

            if(db.isNotEmpty()) mSize = db.size
            dotsIndicator.count = mSize

            horizontalInfiniteCycleViewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                    if (previousPage<position) {
                        if (currentPage != mSize-1 ) currentPage++ else currentPage = 0
                        dotsIndicator.selection = currentPage
                        previousPage = position
                    }
                    else if (previousPage>position) {
                        if (currentPage == 0) currentPage = mSize-1 else currentPage--
                        dotsIndicator.selection = currentPage
                        previousPage = position
                    }
                }override fun onPageSelected(position: Int) {}
                override fun onPageScrollStateChanged(state: Int) {} })
        })

        historyRecyclerView.layoutManager =
            LinearLayoutManager(requireContext())
        homeViewModel.liveDbHistory.observe(viewLifecycleOwner, {
            historyRecyclerView.adapter =
                HistoryAdapter(requireContext(), it, this)

        })
    }

    override fun onClick(v: View?) {

    }
}