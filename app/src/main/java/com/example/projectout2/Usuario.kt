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

    val u1 = Usuario("Elrecoal","Ellie", "Apellido1", "1", "1", 1, R.drawable.foto)
    val u2 = Usuario("Pepe","ZhenYu", "Apellido2", "2", "2", 2, R.drawable.foto)
    val u3 = Usuario("Ricardoooo","Ricardo", "Apellido3", "3", "3", 3, R.drawable.foto)
    return listOf(u1,u2,u3)

}
fun Comprobar(email:String,password:String): Usuario? {
    var listaUsuarios = generarListaUsuarios()
    for (usuario in listaUsuarios){
        if (email.equals(usuario.email)&& password.equals(usuario.password)){
            return usuario
        }
    }
    return null
}