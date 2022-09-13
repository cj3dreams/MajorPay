package com.cj3dreams.majorpay.view.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.cj3dreams.majorpay.R
import com.cj3dreams.majorpay.model.history.Result
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.SimpleDateFormat


class HistoryDetailRoundedBottomSheetDialogFragment : BottomSheetDialogFragment() {
    private lateinit var model: Result
    private lateinit var dHLogoImgView: ImageView
    private lateinit var dHLogoTx: TextView
    private lateinit var dHAmountTx: TextView
    private lateinit var dHNumberTx: TextView
    private lateinit var dHDateTx: TextView
    private lateinit var dHPaymentIdTx: TextView
    private lateinit var homeBtnMoreHistory: Button

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        BottomSheetDialog(requireContext(), theme)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_history_detail_rounded_bottom_sheet_dialog, container, false)

        dHLogoImgView = view.findViewById(R.id.dHLogoImgView)
        dHLogoTx = view.findViewById(R.id.dHLogoTx)
        dHAmountTx = view.findViewById(R.id.dHAmountTx)
        dHNumberTx = view.findViewById(R.id.dHNumberTx)
        dHDateTx = view.findViewById(R.id.dHDateTx)
        dHPaymentIdTx = view.findViewById(R.id.dHPaymentIdTx)
        homeBtnMoreHistory = view.findViewById(R.id.homeBtnMoreHistory)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.setOnShowListener { dialog ->
            val d = dialog as BottomSheetDialog
            val bottomSheet = d.findViewById<View>(R.id.design_bottom_sheet) as FrameLayout
            val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        dHLogoImgView.setImageResource(model.icon.toInt())
        dHLogoTx.append(model.category)
        dHAmountTx.append(model.amount)
        dHNumberTx.append(model.to)
        dHDateTx.append(SimpleDateFormat("dd.MM.yy HH:mm").format(
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(model.updatedAt))
           .toString())
        dHPaymentIdTx.append(model.objectId)
        homeBtnMoreHistory.setOnClickListener { dismiss() }

    }

    companion object {
        fun instanceHistoryDetailFragment(model: Result) =
            HistoryDetailRoundedBottomSheetDialogFragment().apply { this.model = model }
    }
}