package com.example.projectout2

data class Publicacion(
    val usuario: Usuario,
    val imagen: Int,
    val fecha: String,
    val hora: String,
    val descripcion: String,
    var likes: Int
)