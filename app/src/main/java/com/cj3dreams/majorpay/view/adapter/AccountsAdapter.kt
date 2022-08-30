package com.cj3dreams.majorpay.view.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import com.cj3dreams.majorpay.R
import com.cj3dreams.majorpay.model.card.Result

class AccountsAdapter(private val list: List<Result>
, private val context: Context, private val onClickListener: View.OnClickListener) :
    PagerAdapter(){

    private val listColorTemp = listOf<Int?>(
        R.drawable.card_teal_200,
        R.drawable.card_purple_200,
        R.color.whiteGray)


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.item_card, container, false)

        val itemClick = view.findViewById(R.id.itemCardClick) as RelativeLayout
        val itemImgViewBackground = view.findViewById(R.id.itemCardImgViewBackground) as ImageView
        val itemLogo = view.findViewById(R.id.itemCardLogo) as ImageView
        val itemNumbers = view.findViewById(R.id.itemCardNumbers) as TextView
        val itemBalance = view.findViewById(R.id.itemCardBalance) as TextView

        val itemData = list[position]

        itemNumbers.text = itemData.numbers.substring(0,4) + " **** **** " + itemData.numbers.substring(15,19)
        itemBalance.text = itemData.balance + "$"
        itemLogo.setImageDrawable(Drawable.createFromStream(context.assets.open("icons/" + itemData.belongingCard + ".png"), null))
        itemImgViewBackground.setImageResource(listColorTemp[position]!!)
        container.addView(view)
        itemClick.setOnClickListener { Toast.makeText(context, "Clicked card № $position", Toast.LENGTH_SHORT).show() }
        return view
    }

    override fun getCount() = list.size


}