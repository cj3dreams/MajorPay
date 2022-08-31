package com.cj3dreams.majorpay.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cj3dreams.majorpay.model.history.Result
import com.cj3dreams.majorpay.repo.DataRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.cj3dreams.majorpay.source.remote.ResultResponse
import com.cj3dreams.majorpay.usecase.*

class HistoryViewModel(private val dataRepository: DataRepositoryImpl): ViewModel()  {

    val liveDbHistory: MutableLiveData<List<Result>> = MutableLiveData()
    var errorMessage: MutableLiveData<String> = MutableLiveData()
    var isDbEmpty: MutableLiveData<Boolean> = MutableLiveData()


    private val getAllHistoryUseCase get() = GetAllHistoryUseCase(dataRepository)
    private val getAllHistoryFromLocalUseCase get() = GetAllHistoryFromLocalUseCase(dataRepository)
    private val saveAllHistoryToLocalUseCase get() = SaveAllHistoryToLocalUseCase(dataRepository)


    fun getAllHistoryFromLocal() =
        viewModelScope.launch {
            getAllHistoryFromLocalUseCase.invoke().collect { values ->
                liveDbHistory.value = values
                if(!values.isNullOrEmpty()) isDbEmpty.postValue(false)
                else isDbEmpty.postValue(true)
            }
        }


    fun getAllHistory() = viewModelScope.launch(Dispatchers.IO) {
        when (val dataResult = getAllHistoryUseCase.invoke()) {
            is ResultResponse.Success -> saveAllHistoryToLocalUseCase.invoke(dataResult.data)
            is ResultResponse.Error -> {errorMessage.postValue(dataResult.exception.message)}
        }
    }
}