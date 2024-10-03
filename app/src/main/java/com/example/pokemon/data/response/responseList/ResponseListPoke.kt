package com.example.pokemon.data.response.responseList

data class ResponseListPoke(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)