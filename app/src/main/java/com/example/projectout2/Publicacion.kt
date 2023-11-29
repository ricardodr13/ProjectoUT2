package com.example.projectout2
/*
usuario: Objeto que contiene el usuario autor de la publicacion
imagen: Id de la imagen en R.drawable
fecha: Fecha de publicacion
hora: Hora de publicacion
descripcion: Texto que acompaña a la imagen
*/


data class Publicacion(

    val usuario: Usuario,
    val imagen: Int,
    val fecha: String,
    val hora: String,
    val titulo: String,
    val descripcion: String,
    var likes: Int

)

//Funcion a la que se introduce una lista de usuarios y crea dos publicaciones por usuario
fun generarListaPublicaciones(usuarios: List<Usuario>): List<Publicacion> {
    val publicaciones = mutableSetOf<Publicacion>()

    for (u: Usuario in usuarios) {
        val publicacion1 =
            Publicacion(u, R.drawable.foto, "13 de noviembre", "19:09", "Titulo1", "Descripcion1", 93)
        val publicacion2 =
            Publicacion(u, R.drawable.foto, "28 de febrero", "09:19", "Titulo2", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", 39)
        publicaciones.add(publicacion1)
        publicaciones.add(publicacion2)
    }

    return publicaciones.toList()

}