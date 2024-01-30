package com.mobile.ewallet.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mobile.ewallet.R
import com.mobile.ewallet.fragment.HomeFragment
import com.mobile.ewallet.fragment.SettingFragment
import com.mobile.ewallet.fragment.StatisticFragment
import com.mobile.ewallet.fragment.WalletFragment

class DashboardActivity: AppCompatActivity() {
    private lateinit var bottomNav : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        loadFragment(HomeFragment())
        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                    true
                }

                R.id.wallet -> {
                    loadFragment(WalletFragment())
                    true
                }

                R.id.statistic -> {
                    loadFragment(StatisticFragment())
                    true
                }

                R.id.settings -> {
                    loadFragment(SettingFragment())
                    true
                }

                else -> { false}
            }
        }
    }
    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }
}