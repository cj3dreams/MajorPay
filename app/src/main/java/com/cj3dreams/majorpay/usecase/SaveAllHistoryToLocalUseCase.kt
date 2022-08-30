package com.cj3dreams.majorpay.usecase

import com.cj3dreams.majorpay.model.history.Result
import com.cj3dreams.majorpay.repo.DataRepository

class SaveAllHistoryToLocalUseCase (private val dataRepository: DataRepository) {
    suspend operator fun invoke(historyList: List<Result>) =
        dataRepository.saveAllHistoryToLocal(historyList)
}