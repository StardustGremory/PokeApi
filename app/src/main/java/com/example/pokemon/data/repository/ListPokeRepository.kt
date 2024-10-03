package com.example.pokemon.data.repository

import com.example.pokemon.data.response.responseList.ResponseListPoke
import com.example.pokemon.data.service.ListService
import retrofit2.Response
import javax.inject.Inject

class ListPokeRepository @Inject constructor(private val listService: ListService){
    suspend fun getListPoke(): Response<ResponseListPoke> {
        return listService.getListPoke()
    }
}