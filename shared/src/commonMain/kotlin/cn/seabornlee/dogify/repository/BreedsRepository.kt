package cn.seabornlee.dogify.repository

import cn.seabornlee.dogify.model.Breed
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.supervisorScope

class BreedsRepository internal constructor(
    private val remoteSource: BreedsRemoteSource
) {
    suspend fun get() = fetch()
    suspend fun fetch() = supervisorScope {
        remoteSource.getBreeds().map {
            async {
                Breed(
                    name = it, imageUrl =
                    remoteSource.getBreedImage(it)
                )
            }
        }.awaitAll()
    }
}