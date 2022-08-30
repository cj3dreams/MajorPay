package com.cj3dreams.majorpay.usecase

import com.cj3dreams.majorpay.model.card.Result
import com.cj3dreams.majorpay.repo.DataRepository

class UpdateCardUseCase (private val dataRepository: DataRepository) {
    suspend operator fun invoke(objectId: String, balance: String) =
        dataRepository.updateCard(objectId, balance)
}