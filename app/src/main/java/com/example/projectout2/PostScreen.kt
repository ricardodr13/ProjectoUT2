package com.example.projectout2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostScreen(publicacion: Publicacion) {

    Scaffold(topBar = {
        TopAppBar(title = {
            Row(
                modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = publicacion.usuario.fotoPerfil),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(40.dp)
                )
                Text(
                    text = "@" + publicacion.usuario.nombreUsuario,
                    modifier = Modifier.padding(10.dp, 0.dp)
                )
            }

        })
    }) { innerPadding ->

        Column(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Box(Modifier.fillMaxWidth()){
                Image(
                    painter = painterResource(id = publicacion.imagen),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.Center) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Fecha: " + publicacion.fecha + ". Hora: " + publicacion.hora,
                        Modifier.weight(10f)
                    )

                    var liked by remember { mutableStateOf(false) }

                    Text(text = "" + publicacion.likes)

                    IconToggleButton(checked = liked, onCheckedChange = {
                        liked = !liked
                        if (liked) {
                            publicacion.likes += 1
                        } else {
                            publicacion.likes -= 1
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