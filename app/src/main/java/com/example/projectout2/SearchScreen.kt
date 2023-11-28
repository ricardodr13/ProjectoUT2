package com.example.projectout2


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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

@Composable
fun SearchScreen(listaUsuarios: List<Usuario>) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    var usuarioBuscado by remember { mutableStateOf("") }
                    var listaUsuariosFiltrada by remember { mutableStateOf(listaUsuarios) }

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                    ) {


                        item {

                            Buscador() { opcionSeleccionada ->
                                usuarioBuscado = opcionSeleccionada
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
                            }
                        }


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
            Text(text = "@"+secondaryText)
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Buscador(
    onOptionSelected: (String) -> Unit
) {
    var buscar by remember { mutableStateOf("") }

    Row(modifier = Modifier.fillMaxSize()) {
        TextField(
            value = buscar,
            onValueChange = { value ->
                buscar = value
            },
            label = { Text(text = "Buscar por usuario...") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(15.dp)
        )

        Box(modifier = Modifier.fillMaxHeight()) {
            Button(
                onClick = {
                    onOptionSelected(buscar)
                },
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = "Buscar")
            }
        }
    }
}
