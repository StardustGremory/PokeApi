package com.example.pokemon.data.service

import com.example.pokemon.data.response.responseList.ResponseListPoke
import com.example.pokemon.data.response.responsePoke.ResponsePoke
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ListClient {
    @GET("api/v2/pokemon")
    suspend fun getListPoke(): Response<ResponseListPoke>
    //Character
    @GET("api/v2/pokemon/{id}/")
    suspend fun getCharacterPoke(@Path("id") characterId: Int): Response<ResponsePoke>
}