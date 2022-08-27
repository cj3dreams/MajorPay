package com.cj3dreams.majorpay.usecase

import com.cj3dreams.majorpay.repo.DataRepository

class GetAllCardsUseCase(private val dataRepository: DataRepository) {
    suspend operator fun invoke() = dataRepository.getAllCards()
}