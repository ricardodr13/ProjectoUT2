package com.example.projectout2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    publicaciones: List<Publicacion>,
    navegacion: NavController,
    bottomPadding: PaddingValues,
    onClickPost: (Publicacion) -> Unit
) {

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text("Inicio")
            }, colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary
            )
        )
    }, floatingActionButton = {
        FloatingActionButton(onClick = {
            navegacion.navigate("Nuevo Post")
        }, modifier = Modifier.padding(bottomPadding)) {
            Icon(Icons.Default.Add, contentDescription = null)
        }
    }) { innerPadding ->
        LazyColumn(
            Modifier
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = bottomPadding.calculateBottomPadding()
                )
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            for (p: Publicacion in publicaciones) {

                item {
                    Card(
                        onClick = { onClickPost(p) },
                        modifier = Modifier.padding(10.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Column() {
                            Box {
                                Row(
                                    modifier = Modifier.padding(10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Image(
                                        painter = painterResource(id = p.usuario.fotoPerfil),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .size(40.dp)
                                    )
                                    Text(
                                        text = "@" + p.usuario.nombreUsuario,
                                        modifier = Modifier.padding(10.dp, 0.dp)
                                    )
                                }

                            }

                            Box {
                                Image(
                                    painter = painterResource(p.imagen),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(0.dp, 10.dp, 0.dp, 5.dp)
                                )
                            }

                            Box {
                                Column(modifier = Modifier.padding(10.dp)) {
                                    Text(
                                        text = p.fecha + " · " + p.hora,
                                        fontSize = 15.sp,
                                        fontStyle = FontStyle.Italic
                                    )
                                    Text(
                                        text = p.descripcion, fontSize = 15.sp
                                    )
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