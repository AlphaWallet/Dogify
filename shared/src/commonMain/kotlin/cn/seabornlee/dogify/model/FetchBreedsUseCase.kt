package cn.seabornlee.dogify.model

import cn.seabornlee.dogify.repository.BreedsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class FetchBreedsUseCase : KoinComponent {
    private val breedsRepository: BreedsRepository = get()
    suspend fun invoke(): List<Breed> = breedsRepository.fetch()
}