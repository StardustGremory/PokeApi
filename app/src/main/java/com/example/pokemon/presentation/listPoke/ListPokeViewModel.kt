package com.example.pokemon.presentation.listPoke

import android.app.Activity
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.data.model.ModelListPoke
import com.example.pokemon.domain.GetListPokeUseCase
import com.example.pokemon.domain.GetPokemonStats
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListPokeViewModel @Inject constructor(
    private val getListUseCase: GetListPokeUseCase,
    private val getPokeStats: GetPokemonStats,
    private val auth: FirebaseAuth
) : ViewModel() {

    private val _listCharacters = mutableStateListOf<ModelListPoke>()
    val listCharacters: List<ModelListPoke> = _listCharacters

    private val _showD = MutableLiveData<Boolean>()
    val showD: MutableLiveData<Boolean> = _showD

    fun callGetList(){
        viewModelScope.launch {
            val call = getListUseCase.getListPoke()
            if(call.body() != null){
                val list = call.body()?.results
                if (!list.isNullOrEmpty()) {
                    for (x in list.indices) {
                        val callPoke = getPokeStats.getPoke(x+1)
                        if (callPoke.body() != null){
                            val model = callPoke.body()
                            _listCharacters.add(
                                ModelListPoke(
                                    model?.name ?: "Unknown",
                                    model?.sprites?.other?.officialArtwork?.frontDefault,
                                    model?.stats?.get(0)?.baseStat,
                                    model?.stats?.get(1)?.baseStat,
                                    model?.stats?.get(2)?.baseStat,
                                    model?.stats?.get(3)?.baseStat,
                                    model?.stats?.get(4)?.baseStat,
                                    model?.stats?.get(5)?.baseStat
                                )
                            )
                        }
                    }
                }
            }
        }
        _showD.value = false
    }

    fun logout(activity: Activity?) {
        auth.signOut()
        activity?.finish()
    }
}