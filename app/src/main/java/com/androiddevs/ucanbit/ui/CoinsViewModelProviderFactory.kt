package com.androiddevs.ucanbit.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androiddevs.ucanbit.repository.CoinsRepository



class CoinsViewModelProviderFactory(
    val coinsRepository: CoinsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CoinsViewModel(coinsRepository) as T
    }
}