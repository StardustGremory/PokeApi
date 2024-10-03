package com.example.pokemon.presentation.login

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemon.presentation.listPoke.ListPokeActivity
import com.example.pokemon.domain.LoginFireUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginFire: LoginFireUseCase) : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    fun loginFirebase(context: Context) {
        _email.value = "ing.franciscov@outlook.com"
        _password.value = "PokeApi.1234"

        if(!_email.value.isNullOrEmpty() && _password.value!!.length>6){
            loginFire.loginFire(_email.value!!, _password.value!!){
                if(it){
                    context.startActivity(Intent(context, ListPokeActivity::class.java))
                }else{
                    Log.i("FIREBASE", "UnSucces")
                }
            }
        }
    }
}