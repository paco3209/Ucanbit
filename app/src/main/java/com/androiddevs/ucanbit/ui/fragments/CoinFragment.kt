package com.androiddevs.ucanbit.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.androiddevs.ucanbit.R
import com.androiddevs.ucanbit.ui.CoinsActivity
import com.androiddevs.ucanbit.ui.CoinsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_article.*
import kotlinx.android.synthetic.main.item_article_preview.view.*
import kotlin.math.log10

class CoinFragment: Fragment(R.layout.fragment_article) {

    lateinit var viewModel: CoinsViewModel
    val args: CoinFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= (activity as CoinsActivity).viewModel

        val coin = args.coin

        fab.setOnClickListener {
            viewModel.saveCoin(coin)
            Snackbar.make(view,"Coin Save Succesfully", Snackbar.LENGTH_SHORT).show()
        }



        txtCurrentPrice.text = String.format("%.2f",coin.current_price) + " US$"
        if(coin.price_change_24h < 0){
            txtPriceChange24.setTextColor(resources.getColor((R.color.colorDown)))
            txtPricePercentageChange24.setTextColor(resources.getColor(R.color.colorDown))
        }else{
            txtPriceChange24.setTextColor(resources.getColor(R.color.colorUP))
            txtPricePercentageChange24.setTextColor(resources.getColor(R.color.colorUP))
        }

        txtMarketCap.text = coin.market_cap.toString()
        txtCirculating.text = coin.circulating_supply.toString()
        txtHigh24.text = coin.high_24h.toString()
        txtLow24.text = coin.low_24h.toString()
    }



}