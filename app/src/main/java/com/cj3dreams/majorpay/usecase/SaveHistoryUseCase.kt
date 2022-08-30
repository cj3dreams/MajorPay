package com.cj3dreams.majorpay.usecase

import com.cj3dreams.majorpay.model.card.Result
import com.cj3dreams.majorpay.repo.DataRepository

class SaveHistoryUseCase (private val dataRepository: DataRepository) {
    suspend operator fun invoke(category: String, icon: String, amount: String, to: String, type: String) =
        dataRepository.saveHistory(category, icon, amount, to, type)
}