package com.cj3dreams.majorpay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.cj3dreams.majorpay.view.ui.*
import com.google.android.material.floatingactionbutton.FloatingActionButton

import nl.joery.animatedbottombar.AnimatedBottomBar

class MainActivity : AppCompatActivity(), AnimatedBottomBar.OnTabInterceptListener {
    private lateinit var fab: FloatingActionButton
    lateinit var nameOfFrgTx: TextView
    lateinit var animatedBottomBar: AnimatedBottomBar
    lateinit var transactionRoundedBottomSheetDialogFragment: TransactionRoundedBottomSheetDialogFragment
    lateinit var topBar: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        changeFrg(HomeFragment())

        with(window){
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        }

        topBar = findViewById(R.id.mainTopBar)
        transactionRoundedBottomSheetDialogFragment = TransactionRoundedBottomSheetDialogFragment()
        fab = findViewById(R.id.mainFab)
        fab.setOnClickListener {
            transactionRoundedBottomSheetDialogFragment.show(supportFragmentManager, "Transaction")
        }
        nameOfFrgTx = findViewById(R.id.homeNameOfFrgTx)
        animatedBottomBar = findViewById(R.id.mainAnimatedBar)
        animatedBottomBar.setOnTabInterceptListener(this)

    }

    override fun onTabIntercepted(lastIndex: Int, lastTab: AnimatedBottomBar.Tab?,
        newIndex: Int, newTab: AnimatedBottomBar.Tab): Boolean {

        if (newTab.id == lastTab?.id) return false
        when(newTab.id){
            R.id.menu_home -> changeFrg(HomeFragment())
            R.id.menu_history -> changeFrg(HistoryFragment())
            R.id.menu_reports -> changeFrg(ReportsFragment())
            R.id.menu_profile -> changeFrg(ProfileFragment())
        }
        return true
    }

    fun changeFrg(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFrgView, fragment)
            .commit()
    }
}