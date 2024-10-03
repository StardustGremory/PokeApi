package com.example.pokemon.domain

import com.example.pokemon.data.repository.ListPokeRepository
import com.example.pokemon.data.response.responseList.ResponseListPoke
import retrofit2.Response
import javax.inject.Inject

class GetListPokeUseCase @Inject constructor(private val listPokeRepository: ListPokeRepository){
    suspend fun getListPoke(): Response<ResponseListPoke> {
        return listPokeRepository.getListPoke()
    }
}