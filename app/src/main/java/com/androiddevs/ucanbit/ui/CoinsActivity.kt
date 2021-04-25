package com.androiddevs.ucanbit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.androiddevs.ucanbit.R
import com.androiddevs.ucanbit.db.CoinDatabase
import com.androiddevs.ucanbit.repository.CoinsRepository
import kotlinx.android.synthetic.main.activity_coins.*

class CoinsActivity : AppCompatActivity() {

    lateinit var viewModel: CoinsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coins)

        val coinsRepository = CoinsRepository(CoinDatabase(this))
        val viewModelProviderFactory = CoinsViewModelProviderFactory(coinsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get((CoinsViewModel::class.java))

        bottomNavigationView.setupWithNavController(coinsNavHostFragment.findNavController())

    }
}
