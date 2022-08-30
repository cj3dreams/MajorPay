package com.cj3dreams.majorpay.view.ui

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cj3dreams.majorpay.MainActivity
import com.cj3dreams.majorpay.R
import com.cj3dreams.majorpay.model.history.Result
import com.cj3dreams.majorpay.model.transaction.TransactionTypeModel
import com.cj3dreams.majorpay.utils.AppConstants
import com.cj3dreams.majorpay.view.adapter.TransactionAdapter
import com.cj3dreams.majorpay.vm.HomeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class TransactionRoundedBottomSheetDialogFragment: BottomSheetDialogFragment(), View.OnClickListener{
    private lateinit var transactionRecyclerView: RecyclerView
    private lateinit var paymentView: RelativeLayout
    private lateinit var transactionAmountTx: TextView
    private lateinit var transactionAmountEdTx: EditText
    private lateinit var transactionNumberTx: TextView
    private lateinit var transactionNumberEdTx: EditText
    private lateinit var transactionBtnCancel: MaterialButton
    private lateinit var transactionBtnOk: MaterialButton
    private lateinit var transactionLogoImgView: ImageView
    private lateinit var transactionLogoTx: TextView
    private val homeViewModel: HomeViewModel by viewModel()

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        BottomSheetDialog(requireContext(), theme)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_transaction_rounded_bottom_sheet_dialog, container, false)

        transactionRecyclerView = view.findViewById(R.id.transactionRW)
        paymentView = view.findViewById(R.id.transactionRL)

        transactionAmountTx = view.findViewById(R.id.transactionAmountTx)
        transactionAmountEdTx = view.findViewById(R.id.transactionAmountEdTx)
        transactionNumberTx = view.findViewById(R.id.transactionNumberTx)
        transactionNumberEdTx = view.findViewById(R.id.transactionNumberEdTx)
        transactionBtnCancel = view.findViewById(R.id.transactionBtnCancel)
        transactionBtnOk = view.findViewById(R.id.transactionBtnOk)
        transactionLogoImgView = view.findViewById(R.id.transactionLogoImgView)
        transactionLogoTx = view.findViewById(R.id.transactionLogoTx)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        transactionRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        transactionRecyclerView.adapter =
            TransactionAdapter(requireContext(), AppConstants.listOfTheTypeTransaction, this)

        transactionBtnCancel.setOnClickListener(this)
        transactionBtnOk.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.tItemClick -> {
                val tag = v.tag as TransactionTypeModel
                when (tag.id){
                    0->  transactionRecyclerView.adapter =
                        TransactionAdapter(requireContext(), AppConstants.listOfTheMobilePayment, this)
                    in 100..105 -> {
                        transactionRecyclerView.visibility = View.INVISIBLE

                        transactionLogoTx.text = "Оплата за " + tag.name
                        transactionLogoImgView.setImageResource(tag.icon)
                        transactionLogoImgView.tag = tag.icon
                        transactionLogoTx.tag = tag.name
                        paymentView.visibility = View.VISIBLE

                    }
                }
            }
            R.id.transactionBtnCancel -> {
                transactionNumberEdTx.text.clear()
                transactionAmountEdTx.text.clear()
                this.dismiss().apply {

                }
            }
            R.id.transactionBtnOk -> {
                homeViewModel.saveHistory(Result(
                    transactionAmountEdTx.text.toString(), transactionLogoTx.tag.toString(),
                    "", transactionLogoImgView.tag.toString(),"",
                    transactionNumberEdTx.text.toString(), "outgoing",
                    ""
                )){}
                homeViewModel.outgoingUpdateCard(0,"Yb9MrheUom",
                    transactionAmountEdTx.text.toString().toInt())
                {
                    dismiss().apply {
                        //Временное решение проблемы :)
                        startActivity(Intent(requireActivity(), MainActivity::class.java)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                        requireActivity().overridePendingTransition(0, 0)
                        //Временное решение проблемы :(
                    }
                }
            }
        }
    }
}