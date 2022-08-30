package com.cj3dreams.majorpay.usecase

import com.cj3dreams.majorpay.repo.DataRepository

class GetAllHistoryFromLocalUseCase(private val dataRepository: DataRepository) {
    operator fun invoke() = dataRepository.getAllHistoryFromLocal()
}