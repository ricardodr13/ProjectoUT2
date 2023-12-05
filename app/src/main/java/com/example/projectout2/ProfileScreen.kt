package com.example.projectout2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//pasar el navhost como parametro adicional
fun ProfileScreen(
    publicaciones: List<Publicacion>,
    vm: UsuarioViewModel,
    ncPrograma: NavController,
    navController: NavController,
    bottonPadding: PaddingValues,
    OnClickPost: (Publicacion) -> Unit
) {
    val openAlertDialog = remember { mutableStateOf(false) }
    var state by remember { mutableStateOf(0) }

    val titles = listOf("Publicaciones", "Ajustes")
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text("Login")
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary
            )
        )
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = innerPadding.calculateTopPadding() + 5.dp,
                    bottom = bottonPadding.calculateBottomPadding()
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            //esta caja contiene la imagen de perfil del usuario y el boton para editarla
            Box(contentAlignment = Alignment.BottomEnd) {
                Image(
                    painter = painterResource(id = vm.fotoPerfil),
                    contentDescription = "imagen",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(200.dp),
                    contentScale = ContentScale.Crop
                )
                IconButton(
                    onClick = {
                        // boton inutil
                    },
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(color = Color.Gray)
                        .size(50.dp)
                ) {
                    Icon(Icons.Filled.Edit, contentDescription = "cambiar imagen")
                }
            }

            //Esta columna contiene los datos del usuario (nombre y nombre de usuario)
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                //cambiar los textos por los datos del usuario.
                Text(
                    text = vm.nombre + " " + vm.apellido,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineLarge
                )
                Text(vm.nombreUsuario, style = MaterialTheme.typography.titleMedium)
            }

            Column {
                TabRow(selectedTabIndex = state) {
                    titles.forEachIndexed { index, title ->
                        Tab(
                            text = { Text(title) },
                            onClick = { state = index },
                            selected = (index == state)
                        )
                    }
                }
                if (state == 0) {
                    LazyColumn(
                        Modifier
                            .padding()
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        for (p: Publicacion in publicaciones) {
                            if (p.usuario.nombreUsuario == vm.nombreUsuario) {
                                item {
                                    Card(
                                        onClick={OnClickPost(p)},
                                        modifier = Modifier
                                            .padding(10.dp),
                                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)

                                    ) {
                                        Column {
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
                                                        text = p.descripcion,
                                                        fontSize = 15.sp
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

                if (state == 1) {

                    //Esta Columna contiene los 2 botones para ir a cambiar los datos personales y el de seguridad.
                    Column(
                        verticalArrangement = Arrangement.Top,
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .fillMaxWidth()
                    ) {
                        OutlinedButton(
                            onClick = { ncPrograma.navigate("Datos") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            shape = RoundedCornerShape(0.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
                        )
                        {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    "Cambiar datos personales",
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                                Icon(
                                    Icons.Filled.KeyboardArrowRight,
                                    contentDescription = "a",
                                    tint = Color.Black
                                )
                            }
                        }
                        OutlinedButton(
                            onClick = { ncPrograma.navigate("Seguridad") },
                            Modifier
                                .height(50.dp)
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(0.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)

                        )
                        {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    "Seguridad",
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                                Icon(
                                    Icons.Filled.KeyboardArrowRight,
                                    contentDescription = "a",
                                    tint = Color.Black
                                )
                            }
                        }
                    }

                    Button(
                        onClick = {
                            openAlertDialog.value = true
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp)
                    ) {
                        Text(text = "Log out")
                    }
                    when {
                        openAlertDialog.value -> {
                            PopUpLogin(
                                onDismissRequest = { openAlertDialog.value = false },
                                onConfirmation = {
                                    openAlertDialog.value = false
                                    println("Saliendo...")
                                    navController.navigate("Login")
                                },
                                dialogTitle = "Log Out",
                                dialogText = "¿Desea volver a la pantalla de Login?"
                            )
                        }
                    }

                }
            }

        }
    }
}