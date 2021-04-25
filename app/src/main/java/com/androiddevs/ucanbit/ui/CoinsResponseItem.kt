package com.androiddevs.ucanbit.ui

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "coins"
)
data class CoinsResponseItem(

    @PrimaryKey val id: String,
    val ath: Double,
    val ath_change_percentage: Double,
    val ath_date: String,
    val atl: Double,
    val atl_change_percentage: Double,
    val atl_date: String,
    val circulating_supply: Double,
    val current_price: Double,
    val fully_diluted_valuation: Long,
    val high_24h: Double,

    val image: String,
    val last_updated: String,
    val low_24h: Double,
    val market_cap: Long,
    val market_cap_change_24h: Double,
    val market_cap_change_percentage_24h: Double,
    val market_cap_rank: Int,
    val max_supply: Double,
    val name: String,
    val price_change_24h: Double,
    val price_change_percentage_1h_in_currency: Double,
    val price_change_percentage_24h: Double,
    val price_change_percentage_30d_in_currency: Double,
    val price_change_percentage_7d_in_currency: Double,
    val symbol: String,
    val total_supply: Double,
    val total_volume: Long
)