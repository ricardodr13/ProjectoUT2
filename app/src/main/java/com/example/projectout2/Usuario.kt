package com.example.projectout2

/*
    nombre: nombre real
    nombreUsuario: nombre mostrado en pantalla
    apellido: usuario
    email: correo de la cuenta del usuario, usado para registrarse y logearse
    password: Contraseña que el usuario ha establecido para iniciar sesion
    fechaNacimiento: Dato para el perfil.
    fotoPerfil: Id de la foto usada para la foto de perfil en Buscar y en los posts
*/
data class Usuario(

    val nombreUsuario: String,
    val nombre: String,
    val apellido: String,
    val password: String,
    val email: String,
    val fechaNacimiento: Int,
    val fotoPerfil: Int

)
//Funcion para generar tres usuarios determinados
fun generarListaUsuarios(): List<Usuario> {

    val u1 = Usuario("Elrecoal","Ellie", "Apellido1", "contraseña1", "email1", 1, R.drawable.foto)
    val u2 = Usuario("Pepe","Zhen Yu", "Apellido2", "contraseña2", "email2", 2, R.drawable.foto)
    val u3 = Usuario("Ricardoooo","Ricardo", "Apellido3", "contraseña3", "email3", 3, R.drawable.foto)
    return listOf(u1,u2,u3)

}