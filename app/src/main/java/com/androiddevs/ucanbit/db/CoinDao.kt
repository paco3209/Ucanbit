package com.androiddevs.ucanbit.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.androiddevs.ucanbit.models.CoinsResponseItem

@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(coin: CoinsResponseItem): Long //ver


    @Query("SELECT * FROM coins")
    fun getAllCoins(): LiveData<List<CoinsResponseItem>>

    @Delete
    suspend fun deleteCoin(coin: CoinsResponseItem)

}