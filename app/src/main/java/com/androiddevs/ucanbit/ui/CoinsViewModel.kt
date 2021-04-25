package com.androiddevs.ucanbit.ui

import androidx.lifecycle.ViewModel
import com.androiddevs.ucanbit.repository.CoinsRepository

class CoinsViewModel(
    val coinsRepository: CoinsRepository
) : ViewModel(){

}