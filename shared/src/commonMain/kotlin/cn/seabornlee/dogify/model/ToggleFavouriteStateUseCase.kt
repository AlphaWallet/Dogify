package cn.seabornlee.dogify.model

import cn.seabornlee.dogify.repository.BreedsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ToggleFavouriteStateUseCase : KoinComponent {
    private val breedsRepository: BreedsRepository by inject()

    suspend operator fun invoke(breed: Breed) {
        breedsRepository.update(
            breed.copy(
                isFavourite = !breed.isFavourite
            )
        )
    }
}