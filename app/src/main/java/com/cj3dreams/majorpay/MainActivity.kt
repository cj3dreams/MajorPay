package com.cj3dreams.majorpay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.cj3dreams.majorpay.view.ui.HistoryFragment
import com.cj3dreams.majorpay.view.ui.HomeFragment
import com.cj3dreams.majorpay.view.ui.TransactionRoundedBottomSheetDialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

import nl.joery.animatedbottombar.AnimatedBottomBar

class MainActivity : AppCompatActivity(), AnimatedBottomBar.OnTabInterceptListener {
    private lateinit var animatedBottomBar: AnimatedBottomBar
    private lateinit var fab: FloatingActionButton
    private lateinit var transactionRoundedBottomSheetDialogFragment: TransactionRoundedBottomSheetDialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supp(HomeFragment())

        with(window){
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        }
        transactionRoundedBottomSheetDialogFragment = TransactionRoundedBottomSheetDialogFragment()
        fab = findViewById(R.id.mainFab)
        fab.setOnClickListener {
            transactionRoundedBottomSheetDialogFragment.show(supportFragmentManager, "Transaction")
        }
        animatedBottomBar = findViewById(R.id.mainAnimatedBar)
        animatedBottomBar.setOnTabInterceptListener(this)
    }

    override fun onTabIntercepted(lastIndex: Int, lastTab: AnimatedBottomBar.Tab?,
        newIndex: Int, newTab: AnimatedBottomBar.Tab): Boolean {

        if (newTab.id == lastTab?.id) return false
        when(newTab.id){
            R.id.menu_home -> supp(HomeFragment())
            R.id.menu_history -> supp(HistoryFragment())
        }
        return true
    }

    fun supp(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFrgView, fragment)
            .commit()
    }
}