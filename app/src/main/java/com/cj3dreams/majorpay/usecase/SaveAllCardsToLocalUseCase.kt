package com.cj3dreams.majorpay.usecase

import com.cj3dreams.majorpay.model.card.Result
import com.cj3dreams.majorpay.repo.DataRepository

class SaveAllCardsToLocalUseCase (private val dataRepository: DataRepository) {
    suspend operator fun invoke(cardList: List<Result>) = dataRepository.saveAllCardToLocal(cardList)
}