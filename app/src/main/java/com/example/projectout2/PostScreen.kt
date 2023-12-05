package com.example.projectout2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostScreen(publicacion: Publicacion, navegacion: NavController, bottomPadding: PaddingValues) {

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        navegacion.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "")
                    }
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

            }, colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary
            )
        )
    }) { innerPadding ->

        LazyColumn(
            Modifier
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = bottomPadding.calculateBottomPadding()
                )
                .fillMaxSize()
        ) {
            item {
                Image(
                    painter = painterResource(id = publicacion.imagen),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier.padding(20.dp, 0.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = publicacion.fecha + " · " + publicacion.hora,
                            modifier = Modifier.weight(10f),
                            fontSize = 15.sp,
                            fontStyle = FontStyle.Italic
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
                    Text(
                        text = publicacion.titulo,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(text = publicacion.descripcion, fontSize = 15.sp)
                }
            }
        }
    }
}