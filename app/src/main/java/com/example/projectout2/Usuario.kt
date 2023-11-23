package com.example.projectout2

/*  nombre: nombre en la red social
    apellido: usuario
    email: correo de la cuenta del usuario, usado para registrarse y logearse
    password: Contraseña que el usuario ha establecido para iniciar sesion
    fechaNacimiento: Dato para el perfil.

*/
data class Usuario(

    val nombre: String,
    val apellido: String,
    val password: String,
    val email: String,
    val fechaNacimiento: Int,
    val fotoPerfil: Int

    )
