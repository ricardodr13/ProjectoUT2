package com.example.projectout2

import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen( navController: NavController, listaUsuarios: List<Usuario>) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val contextForToast = LocalContext.current.applicationContext
                        androidx.compose.material.IconButton(
                            onClick = {
                                navController.popBackStack()
                            }
                        ) {
                            Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "")
                        }
                        Text(
                            text = "Seguridad",
                            modifier = Modifier.padding(10.dp, 0.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement
                    .Center,
                horizontalAlignment = Alignment
                    .CenterHorizontally
            ) {
                var dia: List<String> = (1..31).map { it.toString() }
                var mes: List<String> = listOf(
                    "enero", "febrero", "marzo", "abril", "mayo", "junio",
                    "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"
                )
                var anio: List<String> = (1900..2023).map { it.toString() }

                var Nombreusuario by remember { mutableStateOf("") }
                var email by remember { mutableStateOf("") }
                var nombre by remember { mutableStateOf("") }
                var apellido by remember { mutableStateOf("") }
                var contrasena by remember { mutableStateOf("") }
                var Dia by remember { mutableStateOf(true) }
                var Mes by remember { mutableStateOf(true) }
                var Anio by remember { mutableStateOf(true) }

                var texto by remember { mutableStateOf("") }
                var coincidencia by remember { mutableStateOf(false) }

                nombre=Rellenar(dato = "Nombre");
                apellido=Rellenar(dato = "Apellido")
                Nombreusuario = Rellenar(dato = "@Usuario")
                Text(text = "Fecha de Nacimiento")
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Dia=Desplegable(options = dia)
                    Text(text = "/")
                    Mes=Desplegable(options = mes)
                    Text(text = "/")
                    Anio=Desplegable(options = anio)
                }
                email = Rellenar(dato = "Email")
                contrasena=Rellenar(dato = "Contraseña", true)

                Button(onClick = {
                    if (nombre.isBlank() || apellido.isBlank() || Nombreusuario.isBlank() || email.isBlank() || contrasena.isBlank() ||
                        Dia || Mes || Anio) {

                        texto="Rellena todos los campos obligatorios"
                    }
                    else {
                        coincidencia = false
                        for (usuario in listaUsuarios) {
                            if (Nombreusuario.equals(usuario.nombreUsuario)) {
                                coincidencia = true
                                texto = "Nombre de usuario ya existente"
                            } else if (email.equals(usuario.email, ignoreCase = true)) {
                                coincidencia = true
                                texto = "Correo ya registrado"
                            }
                        }
                        if (coincidencia == false) {
                            texto = ""
                            navController.popBackStack()
                        }
                    }
                }) {
                    Text(text = "Sign in")

                }

                Text(
                    text = texto,
                    style = LocalTextStyle.current.copy(
                        color = Color.Red,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    ),
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Rellenar(dato: String, boolean: Boolean = false): String{
    var linea by remember { mutableStateOf("") }

    TextField(
        value = linea,
        onValueChange = { value ->
            linea = value
        },
        label = { Text(text = dato) },
        singleLine = true,
        modifier = Modifier
            .padding(5.dp)
            .clip(RoundedCornerShape(20.dp)),
        visualTransformation = if (boolean) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        }
    )
    return linea
}

@Composable
fun Desplegable(
    options: List<String>,
    modifier: Modifier=Modifier) : Boolean {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }
    var n by remember { mutableStateOf(0)}

    Box(
        modifier = modifier
    ) {
        Text(
            text = "${options[selectedIndex]}",
            modifier = Modifier
                .clickable {
                    expanded = true
                    n++ }
                .padding(16.dp)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEachIndexed { index, option ->
                Text(
                    text = option,
                    modifier = Modifier.clickable {
                        selectedIndex = index
                        expanded = false
                    }
                )
            }
        }
    }
    return n == 0
}

