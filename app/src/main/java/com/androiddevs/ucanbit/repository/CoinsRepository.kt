package com.androiddevs.ucanbit.repository

import com.androiddevs.ucanbit.api.RetrofitInstance
import com.androiddevs.ucanbit.db.CoinDatabase
import com.androiddevs.ucanbit.models.CoinsResponseItem

class CoinsRepository(
    val db: CoinDatabase
) {

        suspend fun getCoins() = RetrofitInstance.api.getCoinsList()

        suspend fun getSearchCoins(searchQuery: String) =
            RetrofitInstance.api.searchCoin(searchQuery,"usd",20,"1h,7d,30d")

        suspend fun upsert(coin:CoinsResponseItem) = db.getCoinDao().upsert(coin)

        fun getSavedCoins() = db.getCoinDao().getAllCoins()

        suspend fun delete(coin:CoinsResponseItem) = db.getCoinDao().deleteCoin(coin)

}