package com.example.pokemon.data.service

import com.example.pokemon.data.response.responsePoke.ResponsePoke
import retrofit2.Response
import javax.inject.Inject

class PokeStatsService @Inject constructor(private val productClient: ListClient){
    suspend fun getPoke(id: Int): Response<ResponsePoke> {
        return productClient.getCharacterPoke(id)
    }
}