package com.cj3dreams.majorpay.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
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
import java.text.SimpleDateFormat
import java.util.*

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
        if (position%2==1) holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.whiteGray))

        holder.itemLogoImgView.setImageResource(itemData.icon.toInt())
        holder.itemLogoNameTx.text = itemData.category
        setTimeText(holder.itemDateTx, convertDate(itemData.createdAt)!!)
        if(itemData.type == "outgoing") {
            holder.itemAmountTx.text = "- " + itemData.amount + " USD"
            holder.itemAmountTx.setTextColor(Color.RED)
        }else{
            holder.itemAmountTx.text = itemData.amount + " USD"
            holder.itemAmountTx.setTextColor(Color.GREEN)
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun setTimeText(textView: TextView, date: Date) {
        val justDate = SimpleDateFormat("dd.MM.yy")
        val yesterday = Calendar.getInstance()
        yesterday.add(Calendar.DATE, -1)
        textView.apply {
            textView.text =
                when (justDate.format(date)) {
                    justDate.format(Calendar.getInstance().time) -> "Сегодня" +
                            SimpleDateFormat(" HH:mm")
                                .format(date)
                    justDate.format(yesterday.time) -> "Вчера" +
                            SimpleDateFormat(" HH:mm").format(date)
                    else -> SimpleDateFormat("dd.MM.yy HH:mm").format(date)
                }
        }
    }
    override fun getItemCount() = list.size

    private fun convertDate(date: String): Date? = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(date)
}