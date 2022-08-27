package com.cj3dreams.majorpay.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.cj3dreams.majorpay.R
import com.cj3dreams.majorpay.view.adapter.AccountsAdapter
import com.cj3dreams.majorpay.vm.HomeViewModel
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private lateinit var horizontalInfiniteCycleViewPager: HorizontalInfiniteCycleViewPager
    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view =  inflater.inflate(R.layout.fragment_home, container, false)

        horizontalInfiniteCycleViewPager = view.findViewById(R.id.homeHorizonInfCycleViewPager)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        horizontalInfiniteCycleViewPager.adapter =
            AccountsAdapter((0..2).toList(), requireContext())
        homeViewModel.getAllCards()
        homeViewModel.mutableLiveData.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
        })
    }
    
}