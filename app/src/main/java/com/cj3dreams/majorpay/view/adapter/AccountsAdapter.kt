package com.cj3dreams.majorpay.view.adapter

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import com.cj3dreams.majorpay.R

class AccountsAdapter(private val list: List<Int>, private val context: Context) :
    PagerAdapter() {

    val listColorTemp = listOf<Int?>(
        R.drawable.ic_purple_card_200,
        R.drawable.ic_teal_card_200,
        R.color.whiteGray)

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.card_item, container, false)
        val itemClick = view.findViewById<RelativeLayout>(R.id.itemCardClick)
        val itemImgViewBackground = view.findViewById<ImageView>(R.id.itemCardImgViewBackground)
        itemImgViewBackground.setImageResource(listColorTemp[position]!!)
        container.addView(view)
        itemClick.setOnClickListener { Toast.makeText(context, "Clicked card № $position", Toast.LENGTH_SHORT).show() }
        return view
    }

    override fun getCount() = list.size

}