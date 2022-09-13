package com.cj3dreams.majorpay.view.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cj3dreams.majorpay.MainActivity
import com.cj3dreams.majorpay.R

class ProfileFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).apply {
            topBar.visibility = View.VISIBLE
            nameOfFrgTx.text = "Профиль"
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }


}