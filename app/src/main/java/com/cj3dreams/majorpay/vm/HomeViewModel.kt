package com.cj3dreams.majorpay.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cj3dreams.majorpay.repo.DataRepositoryImpl
import com.cj3dreams.majorpay.usecase.GetAllCardsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.cj3dreams.majorpay.source.remote.Result

class HomeViewModel(private val dataRepository: DataRepositoryImpl): ViewModel()  {

    val mutableLiveData: MutableLiveData<List<com.cj3dreams.majorpay.model.card.Result>> = MutableLiveData()

    private val getAllCardsUseCase get() = GetAllCardsUseCase(dataRepository)

    fun getAllCards() = viewModelScope.launch(Dispatchers.IO) {
        when (val dataResult = getAllCardsUseCase.invoke()) {
            is Result.Success -> mutableLiveData.postValue(dataResult.data)
            is Result.Error -> { }
        }
    }
}