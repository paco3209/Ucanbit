package com.androiddevs.ucanbit.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevs.ucanbit.models.CoinsResponse
import com.androiddevs.ucanbit.models.CoinsResponseItem
import com.androiddevs.ucanbit.repository.CoinsRepository
import com.androiddevs.ucanbit.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class CoinsViewModel(
    val coinsRepository: CoinsRepository
) : ViewModel(){

    val coins: MutableLiveData<Resource<CoinsResponse>> = MutableLiveData()
    var coinsPage = 1

    val searchCoin: MutableLiveData<Resource<CoinsResponse>> = MutableLiveData()

    init {
        getCoins()
    }

    fun getCoins() = viewModelScope.launch {
        coins.postValue(Resource.Loading())
        val response = coinsRepository.getCoins(coinsPage)
        coins.postValue(handleCoinsResponse(response))
    }

    fun getSearchCoins(coinName: String) = viewModelScope.launch {
        searchCoin.postValue(Resource.Loading())
        val response = coinsRepository.getSearchCoins(coinName)
        searchCoin.postValue(handleSearchCoinResponse(response))
    }

    private fun handleSearchCoinResponse(response: Response<CoinsResponse>): Resource<CoinsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())

    }

    private fun handleCoinsResponse(response: Response<CoinsResponse>): Resource<CoinsResponse>{
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


    fun saveCoin(coin:CoinsResponseItem) = viewModelScope.launch {
        coinsRepository.upsert(coin)
    }

    fun getSavedNews() = coinsRepository.getSavedCoins()

    fun deleteCoin(coin: CoinsResponseItem) = viewModelScope.launch {
        coinsRepository.delete(coin)
    }


}