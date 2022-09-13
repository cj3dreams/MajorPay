package com.cj3dreams.majorpay.view.ui

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager.widget.ViewPager
import com.cj3dreams.majorpay.MainActivity
import com.cj3dreams.majorpay.R
import com.cj3dreams.majorpay.model.history.Result
import com.cj3dreams.majorpay.view.adapter.AccountsAdapter
import com.cj3dreams.majorpay.view.adapter.HistoryAdapter
import com.cj3dreams.majorpay.vm.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import com.rd.PageIndicatorView
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private lateinit var horizontalInfiniteCycleViewPager: ViewPager
    private lateinit var dotsIndicator: PageIndicatorView
    private lateinit var historyRecyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var btnShowMore: Button
    private lateinit var historyDetailRoundedBottomSheetDialogFragment: HistoryDetailRoundedBottomSheetDialogFragment
    private val homeViewModel: HomeViewModel by viewModel()

    private var previousPage = 0
    private var currentPage = 0
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
        btnShowMore = view.findViewById(R.id.homeBtnMoreHistory)
        progressBar = view.findViewById(R.id.homeProgressBar)
        swipeRefreshLayout = view.findViewById(R.id.homeSwipeToUpdate)
        swipeRefreshLayout.setOnRefreshListener(this)
        swipeRefreshLayout.setColorSchemeResources(R.color.mainColor,
            R.color.teal_200, R.color.teal_700)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.isDbEmpty.observe(viewLifecycleOwner, { isDbEmpty ->
            if (isDbEmpty) {
                homeViewModel.errorMessage.observe(viewLifecycleOwner, { errorMsg ->
                    if (!errorMsg.isNullOrEmpty()) Snackbar.make(requireView(), "Проверьте соединение, офлайн просмотр недоступен, требуется первое подключение", 1500).show()
                })
            }else {
                homeViewModel.errorMessage.observe(viewLifecycleOwner, { errorMsg ->
                    if (!errorMsg.isNullOrEmpty()) Snackbar.make(requireView(), "Офлайн просмотр, операции недоступны", 1500).show()
                })
                homeViewModel.isDbEmpty.removeObservers(viewLifecycleOwner)
                homeViewModel.errorMessage.removeObservers(viewLifecycleOwner)
            }
        })

        homeViewModel.liveDbCard.observe(viewLifecycleOwner, { db ->
            horizontalInfiniteCycleViewPager.adapter =
                AccountsAdapter(db, requireContext(), this)

            if(db.isNotEmpty()) mSize = db.size.apply {
                if (this != 0) {
                    progressBar.visibility = View.GONE
                    swipeRefreshLayout.isRefreshing = false
                    btnShowMore.visibility = View.VISIBLE
                }
            }
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
                HistoryAdapter(requireContext(), it, this, true)

        })
        btnShowMore.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.homeBtnMoreHistory -> (activity as MainActivity).apply {
                animatedBottomBar.selectTabAt(1)
                this.changeFrg(HistoryFragment()) }
            R.id.rootItemH -> {
                val tag = v.tag as Result
                historyDetailRoundedBottomSheetDialogFragment = HistoryDetailRoundedBottomSheetDialogFragment.instanceHistoryDetailFragment(tag)
                historyDetailRoundedBottomSheetDialogFragment.show(requireActivity().supportFragmentManager, "Detail")
            }
        }

    }

    override fun onResume() {
        (activity as MainActivity).apply { this.topBar.visibility = View.GONE }
        super.onResume()
    }

    override fun onStop() {
        (activity as MainActivity).apply { this.topBar.visibility = View.VISIBLE }
        super.onStop()
    }

    override fun onRefresh() {
        (activity as MainActivity).apply {
            this.changeFrg(HomeFragment()) }
    }
}