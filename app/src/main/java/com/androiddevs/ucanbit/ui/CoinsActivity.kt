package com.androiddevs.ucanbit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.androiddevs.ucanbit.R
import kotlinx.android.synthetic.main.activity_coins.*

class CoinsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coins)

        bottomNavigationView.setupWithNavController(coinsNavHostFragment.findNavController())

    }
}
