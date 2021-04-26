package com.androiddevs.ucanbit.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.androiddevs.ucanbit.R
import com.androiddevs.ucanbit.ui.CoinsActivity
import com.androiddevs.ucanbit.ui.CoinsViewModel
import kotlinx.android.synthetic.main.fragment_article.*
import kotlin.math.log10

class CoinFragment: Fragment(R.layout.fragment_article) {

    lateinit var viewModel: CoinsViewModel
    val args: CoinFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= (activity as CoinsActivity).viewModel

        val coin = args.coin



        txtCoin.text = coin.name

    }
}