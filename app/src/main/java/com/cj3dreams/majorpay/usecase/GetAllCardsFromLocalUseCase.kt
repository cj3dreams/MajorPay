package com.cj3dreams.majorpay.usecase

import com.cj3dreams.majorpay.repo.DataRepository

class GetAllCardsFromLocalUseCase(private val dataRepository: DataRepository) {
    operator fun invoke() = dataRepository.getAllCardsFromLocal()
}