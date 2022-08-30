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

class HomeViewModel(private val dataRepository: DataRepositoryImpl): ViewModel()  {

    val liveDbCard: MutableLiveData<List<com.cj3dreams.majorpay.model.card.Result>> = MutableLiveData()
    val liveDbHistory: MutableLiveData<List<Result>> = MutableLiveData()
    var errorMessage: MutableLiveData<String> = MutableLiveData()
    var isDbEmpty: MutableLiveData<Boolean> = MutableLiveData(true)
    var sizeOfDb: MutableLiveData<Int> = MutableLiveData(0)

    var isSuccessUpdateCard: MutableLiveData<Boolean> = MutableLiveData(false)

    private val getAllCardsUseCase get() = GetAllCardsUseCase(dataRepository)
    private val updateCardUseCase get() = UpdateCardUseCase(dataRepository)
    private val getAllHistoryUseCase get() = GetAllHistoryUseCase(dataRepository)
    private val saveHistoryUseCase get() = SaveHistoryUseCase(dataRepository)

    private val getAllCardsUseCaseFromLocal get() = GetAllCardsFromLocalUseCase(dataRepository)
    private val getAllHistoryFromLocalUseCase get() = GetAllHistoryFromLocalUseCase(dataRepository)
    private val saveAllCardsToLocalUseCase get() = SaveAllCardsToLocalUseCase(dataRepository)
    private val saveAllHistoryToLocalUseCase get() = SaveAllHistoryToLocalUseCase(dataRepository)


    fun getAllCardsFromLocal() =
        viewModelScope.launch {
            getAllCardsUseCaseFromLocal.invoke().collect { values ->
                liveDbCard.value = values
            }
        }

    fun getAllHistoryFromLocal() =
        viewModelScope.launch {
            getAllHistoryFromLocalUseCase.invoke().collect { values ->
                liveDbHistory.value = values
            }
        }

    fun getAllCards() = viewModelScope.launch(Dispatchers.IO) {
        when (val dataResult = getAllCardsUseCase.invoke()) {
            is ResultResponse.Success -> saveAllCardsToLocalUseCase.invoke(dataResult.data)
            is ResultResponse.Error -> {errorMessage.postValue(dataResult.exception.message)}
        }
    }

    fun getAllHistory() = viewModelScope.launch(Dispatchers.IO) {
        when (val dataResult = getAllHistoryUseCase.invoke()) {
            is ResultResponse.Success -> saveAllHistoryToLocalUseCase.invoke(dataResult.data)
            is ResultResponse.Error -> {errorMessage.postValue(dataResult.exception.message)}
        }
    }

    fun outgoingUpdateCard(cardIndex: Int, objectId: String,
        amount: Int, onComplete: (Boolean) -> Unit) = viewModelScope.launch(Dispatchers.IO) {

        when (val dataResult = getAllCardsUseCase.invoke()) {
            is ResultResponse.Success -> {
                updateCardUseCase
                    .invoke(objectId, (dataResult.data[cardIndex].balance.toInt().minus(amount)).toString())
                        isSuccessUpdateCard.postValue(true)
                getAllCards().apply {
                    onComplete(true)
                }
                    }
            is ResultResponse.Error -> {
                errorMessage.postValue(dataResult.exception.message)
                onComplete(false)
            }
        }
    }
    fun saveHistory(history: Result, onComplete: (Boolean) -> Unit) = viewModelScope.launch(Dispatchers.IO) {

        when (val dataResult = saveHistoryUseCase.invoke(
            history.category, history.icon,
            history.amount, history.to, history.type)) {

            is ResultResponse.Success -> {onComplete(true)}
            is ResultResponse.Error -> {errorMessage.postValue(dataResult.exception.message)}
        }
    }
}