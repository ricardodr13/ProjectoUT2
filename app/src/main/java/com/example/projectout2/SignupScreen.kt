package com.example.projectout2

import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(listaUsuarios: List<Usuario>) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            val dia: List<String> = listOf("Dia") + (1..31).map { it.toString() }
            val mes: List<String> = listOf(
                "Mes", "enero", "febrero", "marzo", "abril", "mayo", "junio",
                "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"
            )
            val anio: List<String> = (1900..2023).map { it.toString() }

            var Nombreusuario by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var texto by remember { mutableStateOf("") }
            var coincidencia by remember { mutableStateOf(false) }

            Rellenar(dato = "Nombre");
            Rellenar(dato = "Apellido")
            Nombreusuario = Rellenar(dato = "@Usuario")
            Row {
                Desplegable(options = dia)
                Desplegable(options = mes)
                Desplegable(options = anio)
            }
            email = Rellenar(dato = "Email")
            Rellenar(dato = "Contraseña", true)

            Button(onClick = {
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
                }
            }) {

            }

            Text(text = texto)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Rellenar(dato: String, boolean: Boolean = false): String {
    var linea by remember { mutableStateOf("") }

    TextField(
        value = linea,
        onValueChange = { value ->
            linea = value
        },
        label = { Text(text = dato) },
        singleLine = true,
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp)),
        visualTransformation = if (boolean) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        }
    )
}

@Composable
fun Desplegable(
    options: List<String>,
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(options.size - 1) }


    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Elige una raza para filtrar: ${options[selectedIndex]}",
            modifier = Modifier
                .clickable { expanded = true }
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
}

