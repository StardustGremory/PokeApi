package com.example.pokemon.domain

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class LoginFireUseCase @Inject constructor(private val auth : FirebaseAuth) {
    fun loginFire(email: String, pwd: String, responseLogin: (Boolean) -> Unit) {
        auth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener{
            if(it.isSuccessful){
                responseLogin(true)
            }else{
                responseLogin(false)
            }
        }
    }
}