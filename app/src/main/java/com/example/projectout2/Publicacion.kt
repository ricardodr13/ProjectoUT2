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
    val descripcion: String,
    var likes: Int

)
//Funcion a la que se introduce una lista de usuarios y crea dos publicaciones por usuario
fun generarListaPublicaciones(usuarios: List<Usuario>): List<Publicacion> {
    val publicaciones = mutableSetOf<Publicacion>()

    for (u: Usuario in usuarios) {
        val publicacion1=Publicacion(u,R.drawable.foto,"13/11","19:09","Foto en negro",93)
        val publicacion2=Publicacion(u,R.drawable.foto,"31/02","09:19","Foto en negro",39)
        publicaciones.add(publicacion1)
        publicaciones.add(publicacion2)
    }

    return publicaciones.toList()

}