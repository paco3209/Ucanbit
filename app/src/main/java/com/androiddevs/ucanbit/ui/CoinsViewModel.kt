package com.androiddevs.ucanbit.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevs.ucanbit.models.CoinsResponse
import com.androiddevs.ucanbit.repository.CoinsRepository
import com.androiddevs.ucanbit.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class CoinsViewModel(
    val coinsRepository: CoinsRepository
) : ViewModel(){

    val coins: MutableLiveData<Resource<CoinsResponse>> = MutableLiveData()
    val coinsPage = 1

    init {
        getCoins()
    }

    fun getCoins() = viewModelScope.launch {
        coins.postValue(Resource.Loading())
        val response = coinsRepository.getCoins()
        coins.postValue(handleCoinsResponse(response))

    }

    private fun handleCoinsResponse(response: Response<CoinsResponse>): Resource<CoinsResponse>{
        if(response.isSuccessful){
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }



}