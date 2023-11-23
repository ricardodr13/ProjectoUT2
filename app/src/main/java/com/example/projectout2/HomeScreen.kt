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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(publicaciones: List<Publicacion>) {

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
            for (p: Publicacion in publicaciones) {

                item {
                    Card(
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Column {
                            Box {
                                Row(
                                    modifier = Modifier.padding(10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Image(
                                        painter = painterResource(id = p.usuario.fotoPerfil),
                                        contentDescription = "Foto de perfil",
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .size(40.dp)
                                    )
                                    Text(
                                        text = "@" + p.usuario.nombre,
                                        modifier = Modifier.padding(10.dp, 0.dp)
                                    )
                                }

                            }

                            Box {
                                Image(
                                    painter = painterResource(p.imagen),
                                    contentDescription = p.descripcion,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(0.dp, 10.dp, 0.dp, 5.dp)
                                )
                            }

                            Box {
                                Column(modifier = Modifier.padding(10.dp)) {
                                    Text(
                                        text = "Fecha: " + p.fecha + "; Hora: " + p.hora,
                                        fontSize = 15.sp
                                    )
                                    Text(text = p.descripcion, fontSize = 20.sp)
                                }

                            }

                        }

                    }

                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

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
                                tint = Color(0xffE91E63), imageVector = if (liked) {
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