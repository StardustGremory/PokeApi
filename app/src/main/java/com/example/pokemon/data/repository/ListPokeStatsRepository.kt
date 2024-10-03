package com.example.pokemon.data.repository

import com.example.pokemon.data.response.responsePoke.ResponsePoke
import com.example.pokemon.data.service.PokeStatsService
import retrofit2.Response
import javax.inject.Inject

class ListPokeStatsRepository @Inject constructor(private val pokeStatsService: PokeStatsService) {
    suspend fun getPoke(id: Int): Response<ResponsePoke> {
        return pokeStatsService.getPoke(id)
    }
}