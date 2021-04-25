package com.androiddevs.ucanbit.api

import com.androiddevs.ucanbit.models.CoinsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinsApi {

    @GET("coins/markets")
    suspend fun getCoinsList(
        @Query("vs_currency")
        vs_currency: String = "usd",
        @Query("per_page")
        per_page: Int = 20,
        @Query("price_change_percentage")
        price_change_percentage: String = "1h,7d,30d"
    ) : Response<CoinsResponse>

    @GET("coins/markets")
    suspend fun searchCoin(
        searchQuery: String,
        @Query("per_page")
        per_page: Int = 20,
        @Query("price_change_percentage")
        price_change_percentage: String = "1h,7d,30d"
    ) : Response<CoinsResponse>


}