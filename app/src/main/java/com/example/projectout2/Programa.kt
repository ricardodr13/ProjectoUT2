package com.example.projectout2

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Programa(vm: UsuarioViewModel, publicaciones:List<Publicacion>, usuarios:List<Usuario>,navController: NavController) {

    var ncProgram = rememberNavController()
    var selectedItem by remember { mutableStateOf(0) }
    var publi by remember { mutableStateOf(publicaciones[0]) }
    val items = listOf("Inicio", "Buscar","Mi Perfil")
    val icons = listOf(Icons.Filled.Home, Icons.Filled.Search,Icons.Filled.AccountCircle)

    Scaffold(
    bottomBar = {
        BottomAppBar {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon ={ Icon(icons[index] , contentDescription = item) },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index
                        ncProgram.navigate(item)})
                }
            }
        }
    }
    ) {innerPadding ->

        NavHost(navController = ncProgram, startDestination = "Inicio") {
            composable("Inicio") { HomeScreen(publicaciones,ncProgram,innerPadding){post -> publi = post
                ncProgram.navigate("Publicacion") } }
            composable("Buscar") { SearchScreen(usuarios,innerPadding,ncProgram) }
            composable("Mi Perfil") { ProfileScreen(publicaciones,vm,ncProgram,navController,innerPadding){post -> publi = post
                ncProgram.navigate("Publicacion")} }
            composable("Seguridad") { SecurityScreen(innerPadding,ncProgram) }
            composable("Datos") { DataChangeScreen(vm,innerPadding,ncProgram) }
            composable("Publicacion") { PostScreen(publi,ncProgram,innerPadding) }
            composable("Nuevo Post") { NewPostScreen(ncProgram, innerPadding)}
        }

    }
}
