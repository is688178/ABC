package com.example.abc

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.abc.config.BatteryLevelReceiver
import com.example.abc.fragments.mainBottom.FragmentABC
import com.example.abc.fragments.mainBottom.FragmentStart
import com.example.abc.fragments.mainBottom.FragmentMaterials
import com.google.android.material.bottomnavigation.BottomNavigationView

class ActivityMain : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var batteryReceiver: BatteryLevelReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigation = findViewById<BottomNavigationView>(R.id.activity_main_bnv_navigation)
        navigation.setOnNavigationItemSelectedListener(this)

        val intentFilter = IntentFilter(Intent.ACTION_BATTERY_LOW)
        batteryReceiver = BatteryLevelReceiver()
        registerReceiver(batteryReceiver, intentFilter)

        // default home fragment view
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.activity_main_fl_main_content,
                FragmentStart()
            )
            .commit()

        //default home selected in bottom menu
        navigation.menu.findItem(R.id.action_start).isChecked = true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_materials -> supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.activity_main_fl_main_content,
                    FragmentMaterials()
                )
                .commit()
            R.id.action_start -> supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.activity_main_fl_main_content,
                    FragmentStart()
                )
                .commit()
            R.id.action_abc -> supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.activity_main_fl_main_content,
                    FragmentABC()
                )
                .commit()
        }
        return true
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(batteryReceiver)
    }

}