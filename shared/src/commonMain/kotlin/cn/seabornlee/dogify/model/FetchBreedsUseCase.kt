package cn.seabornlee.dogify.model

class FetchBreedsUseCase {
    suspend fun invoke(): List<Breed> = listOf(Breed("Test fetch", ""))
}