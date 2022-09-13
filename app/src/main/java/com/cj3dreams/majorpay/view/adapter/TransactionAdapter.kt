package com.cj3dreams.majorpay.view.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.cj3dreams.majorpay.R
import com.cj3dreams.majorpay.model.transaction.TransactionTypeModel

class TransactionAdapter(
    private val context: Context,
    private val list: List<TransactionTypeModel>,
    private val onClickListener: View.OnClickListener)
    : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    class TransactionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemClick = view.findViewById(R.id.tItemClick) as CardView
        val itemImgView = view.findViewById(R.id.tItemImgView) as ImageView
        val itemTextView = view.findViewById(R.id.tItemTx) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_transaction, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val itemData = list[position]
        holder.itemTextView.text = itemData.name
        holder.itemImgView.setImageResource(itemData.icon)
        holder.itemClick.setOnClickListener(onClickListener)
        holder.itemClick.tag = itemData
    }

    override fun getItemCount() = list.size
}