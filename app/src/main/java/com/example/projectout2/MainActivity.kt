package com.example.projectout2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
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
                    val usuarioViewModel: UsuarioViewModel = viewModel<UsuarioViewModel>()
                    val usuarios = generarListaUsuarios()
                    val publicaciones = generarListaPublicaciones(usuarios)
                    //PostScreen(publicaciones[1])
                    //HomeScreen(publicaciones)
                    SearchScreen(usuarios)
                }
            }
        }
    }
}

@Composable
fun Inicio() {
    Text(
        text = "Hola"
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProjectoUT2Theme {
        Inicio()
    }
}