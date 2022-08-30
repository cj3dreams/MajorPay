package com.cj3dreams.majorpay.view.adapter

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cj3dreams.majorpay.R
import com.cj3dreams.majorpay.model.history.Result

class HistoryAdapter(
    private val context: Context,
    private val list: List<Result>, private val onClickListener: View.OnClickListener
) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemLogoImgView = view.findViewById(R.id.hItemImgView) as ImageView
        val itemLogoNameTx = view.findViewById(R.id.hItemNameTx) as TextView
        val itemAmountTx = view.findViewById(R.id.hItemAmountTx) as TextView
        val itemDateTx = view.findViewById(R.id.hItemDateTx) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val itemData = list[position]
        if (position%2==0) holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.whiteGray))

        holder.itemLogoImgView.setImageResource(itemData.icon.toInt())
        holder.itemLogoNameTx.text = itemData.category
        holder.itemAmountTx.text = itemData.amount
        holder.itemDateTx.text = itemData.createdAt
    }

    override fun getItemCount() = list.size
}