package com.example.projectout2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projectout2.ui.theme.ProjectoUT2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectoUT2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var vm: UsuarioViewModel = viewModel<UsuarioViewModel>()
                    val usuarios = generarListaUsuarios()
                    val publicaciones = generarListaPublicaciones(usuarios)
                    Inicio(vm,publicaciones,usuarios)
                }
            }
        }
    }
}

@Composable
fun Inicio(vm:UsuarioViewModel,publicaciones:List<Publicacion>,usuarios:List<Usuario>) {
    var navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Login") {
        composable("Login") { Login(vm,navController) }
        composable("Programa") { Programa(vm,publicaciones,usuarios,navController) }
        composable("Registro") { SignupScreen(navController,usuarios) }
    }
}

