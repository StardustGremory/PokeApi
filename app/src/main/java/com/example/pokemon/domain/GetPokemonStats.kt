package com.example.pokemon.domain

import com.example.pokemon.data.repository.ListPokeStatsRepository
import com.example.pokemon.data.response.responsePoke.ResponsePoke
import retrofit2.Response
import javax.inject.Inject

class GetPokemonStats @Inject constructor(private val listPokeStatsRepository: ListPokeStatsRepository) {
    suspend fun getPoke(id: Int): Response<ResponsePoke> {
        return listPokeStatsRepository.getPoke(id)
    }
}