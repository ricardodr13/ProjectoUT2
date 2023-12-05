package com.example.projectout2


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(listaUsuarios: List<Usuario>, bottomPadding: PaddingValues, navegacion: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        var usuarioBuscado by remember { mutableStateOf("") }
        var listaUsuariosFiltrada by remember { mutableStateOf(listaUsuarios) }



        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Row(
                            modifier = Modifier
                                .padding(end = 20.dp, top = 10.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Box(
                                modifier = Modifier
                                    .height(88.dp)
                                    .fillMaxWidth(0.75f)
                                    .padding(top = 15.dp, end = 20.dp, bottom = 15.dp, start = 5.dp)
                            )
                            {
                                TextField(
                                    value = usuarioBuscado,
                                    onValueChange = { value ->
                                        usuarioBuscado = value
                                    },
                                    label = { Text(text = "Buscar por usuario...") },
                                    singleLine = true,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(22.dp))
                                )
                            }

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Spacer(modifier = Modifier.padding(top = 7.dp))
                                Button(
                                    onClick = {
                                        listaUsuariosFiltrada = if (usuarioBuscado == "") {
                                            listaUsuarios
                                        } else {
                                            listaUsuarios.filter { Usuario ->
                                                Usuario.nombreUsuario.contains(
                                                    usuarioBuscado,
                                                    ignoreCase = true
                                                )
                                            }.toMutableList()
                                        }
                                    },
                                    modifier = Modifier
                                        .padding(top = 5.dp)
                                        .fillMaxWidth()
                                ) {
                                    Text(text = "Buscar")
                                }
                            }
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary)

                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    navegacion.navigate("Nuevo Post")
                }, modifier = Modifier.padding(bottomPadding)) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            }
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = innerPadding.calculateTopPadding(),
                        bottom = bottomPadding.calculateBottomPadding()
                    )
            ) {


                items(listaUsuariosFiltrada) { usuario ->
                    Elemento(usuario)
                    Divider()
                }
            }


            if (listaUsuariosFiltrada.isEmpty())
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Sin resultados")
                }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Elemento(usuario: Usuario) {
    val image = painterResource(id = usuario.fotoPerfil)
    ListItem(
        modifier = Modifier.fillMaxWidth(),
        leadingContent = {
            Image(
                painter = image,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(56.dp)
            )
        },
        headlineText = {
            Text(
                text = usuario.nombre + " " + usuario.apellido,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        },
        supportingText = {
            val secondaryText = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                    append(usuario.nombreUsuario)
                }
            }
            Text(text = "@" + secondaryText)
        }
    )
}

