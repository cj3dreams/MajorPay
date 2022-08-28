package com.cj3dreams.majorpay.view.ui

import android.os.Bundle
import android.os.CpuUsageInfo
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.cj3dreams.majorpay.R
import com.cj3dreams.majorpay.view.adapter.AccountsAdapter
import com.cj3dreams.majorpay.vm.HomeViewModel
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager
import com.rd.PageIndicatorView
import com.rd.animation.type.AnimationType
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private lateinit var horizontalInfiniteCycleViewPager: HorizontalInfiniteCycleViewPager
    private lateinit var dotsIndicator: PageIndicatorView
    private val homeViewModel: HomeViewModel by viewModel()

    private var previousPage = 0
    private var currentPage = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view =  inflater.inflate(R.layout.fragment_home, container, false)

        horizontalInfiniteCycleViewPager = view.findViewById(R.id.homeHorizonInfCycleViewPager)
        dotsIndicator = view.findViewById(R.id.homeDotsIndicator)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        horizontalInfiniteCycleViewPager.adapter =
            AccountsAdapter((0..2).toList(), requireContext())
        dotsIndicator.count = 3
        horizontalInfiniteCycleViewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                if (previousPage<position) {
                    if (currentPage !=2 ) currentPage++ else currentPage = 0
                    dotsIndicator.selection = currentPage
                    previousPage = position
                }
                else if (previousPage>position) {
                    if (currentPage == 0) currentPage = 2 else currentPage--
                    dotsIndicator.selection = currentPage
                    previousPage = position
                }
            }override fun onPageSelected(position: Int) {}
            override fun onPageScrollStateChanged(state: Int) {} })

        homeViewModel.getAllCards()
        homeViewModel.mutableLiveData.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
        })
    }
    
}