package com.example.projectout2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeScreen() {

    val u1 = Usuario("Ellie", "Apellido1", "contraseña1", "email1", 1)
    val u2 = Usuario("Zhen", "Apellido2", "contraseña2", "email2", 2)
    val u3 = Usuario("Ricardo", "Apellido3", "contraseña3", "email3", 3)

    val p1 = Publicacion(u1, R.drawable.foto, "22/11", "22.21", "Foto en negro", 53)
    val p2 = Publicacion(u1, R.drawable.foto, "22/11", "22.21", "Foto en negro", 53)
    val p3 = Publicacion(u2, R.drawable.foto, "22/11", "22.21", "Foto en negro", 53)
    val p4 = Publicacion(u2, R.drawable.foto, "22/11", "22.21", "Foto en negro", 53)
    val p5 = Publicacion(u3, R.drawable.foto, "22/11", "22.21", "Foto en negro", 53)
    val p6 = Publicacion(u3, R.drawable.foto, "22/11", "22.21", "Foto en negro", 53)
    val listaPublicaciones = listOf(p1, p2, p3, p4, p5, p6)

    val color = Color(0xffE91E63)
    Scaffold(topBar = {
        TopAppBar(title = {
            Text("Inicio")
        })
    }, floatingActionButton = {
        FloatingActionButton(onClick = {}) {
            Icon(Icons.Default.Add, contentDescription = "Add")
        }
    }) { innerPadding ->
        LazyColumn(
            Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            for (p: Publicacion in listaPublicaciones) {

                item {
                    Card(
                        modifier = Modifier.size(width = 300.dp, height = 300.dp)
                    ) {
                        Column {
                            Box {
                                Text(
                                    text = "@" + p.usuario.nombre,
                                )
                            }

                            Box {
                                Image(
                                    painter = painterResource(p.imagen),
                                    contentDescription = p.descripcion,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(0.dp, 20.dp)
                                )
                            }

                            Box {
                                Column {
                                    Text(text = p.fecha + "." + p.hora)
                                    Text(text = p.descripcion)
                                }

                            }

                        }

                    }

                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {

                        Text(text = "" + p.likes)


                        var liked by remember { mutableStateOf(false) }

                        IconToggleButton(checked = liked, onCheckedChange = {
                            liked = !liked
                            if (liked) {
                                p.likes += 1
                            } else {
                                p.likes -= 1
                            }
                        }) {
                            Icon(
                                tint = color, imageVector = if (liked) {
                                    Icons.Filled.Favorite
                                } else {
                                    Icons.Default.FavoriteBorder
                                }, contentDescription = null
                            )
                        }
                    }


                }

            }

        }

    }
}