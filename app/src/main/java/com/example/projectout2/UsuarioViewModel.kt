package com.example.projectout2

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class UsuarioViewModel : ViewModel() {

    // Variable de estado que almacena el usuario logueado
    private var _user: Usuario by mutableStateOf(Usuario("","","","","",1,1))

    // Getters para poder sacar los datos del usuario.
    val nombreUsuario get() = _user.nombreUsuario
    val nombre get() = _user.nombre
    val apellido get() = _user.apellido
    val email get() = _user.email
    val password get() = _user.password
    val fechaNacimiento get() = _user.fechaNacimiento
    val fotoPerfil get() = _user.fotoPerfil

    // Funcion para inicializar el usuario que se a logueado
    fun obtenerUsuario(u: Usuario?) {
        if (u != null) {
            _user = u
        }
        else Usuario("","","","","",1,1)
    }
}