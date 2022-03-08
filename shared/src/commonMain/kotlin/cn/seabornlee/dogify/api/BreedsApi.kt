package cn.seabornlee.dogify.api

import cn.seabornlee.dogify.api.model.BreedImageResponse
import cn.seabornlee.dogify.api.model.BreedsResponse
import io.ktor.client.request.*

internal class BreedsApi : KtorApi() {
    suspend fun getBreeds(): BreedsResponse =
        client.get {
            apiUrl("breeds/list")
        }

    suspend fun getRandomBreedImageFor(breed: String):
            BreedImageResponse = client.get {
        apiUrl("breed/$breed/images/random")
    }
}