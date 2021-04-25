package com.androiddevs.ucanbit.repository

import com.androiddevs.ucanbit.api.RetrofitInstance
import com.androiddevs.ucanbit.db.CoinDatabase

class CoinsRepository(
    val db: CoinDatabase
) {

        suspend fun getCoins() = RetrofitInstance.api.getCoinsList()

        suspend fun getSearchCoins(searchQuery: String) =
            RetrofitInstance.api.searchCoin(searchQuery,"usd",20,"1h,7d,30d")

}