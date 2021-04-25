package com.androiddevs.ucanbit.repository

import com.androiddevs.ucanbit.api.RetrofitInstance
import com.androiddevs.ucanbit.db.CoinDatabase

class CoinsRepository(
    val db: CoinDatabase
) {

        suspend fun getCoins() = RetrofitInstance.api.getCoinsList()

}