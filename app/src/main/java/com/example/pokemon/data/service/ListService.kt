package com.example.pokemon.data.service

import com.example.pokemon.data.response.responseList.ResponseListPoke
import retrofit2.Response
import javax.inject.Inject

class ListService @Inject constructor(private val productClient: ListClient) {
    suspend fun getListPoke(): Response<ResponseListPoke> {
        return productClient.getListPoke()
    }
}