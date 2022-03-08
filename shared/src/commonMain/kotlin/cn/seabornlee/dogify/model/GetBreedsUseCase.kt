package cn.seabornlee.dogify.model

class GetBreedsUseCase {
    suspend fun invoke(): List<Breed> = listOf(Breed("Test get", ""))
}