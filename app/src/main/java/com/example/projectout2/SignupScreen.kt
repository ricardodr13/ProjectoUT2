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
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(listaUsuarios: List<Usuario>) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement
                .Center,
            horizontalAlignment = Alignment
                .CenterHorizontally
            ) {
            var dia: List<String> = listOf("Dia") + (1..31).map { it.toString() }
            var mes: List<String> = listOf(
                "Mes", "enero", "febrero", "marzo", "abril", "mayo", "junio",
                "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"
            )
            var anio: List<String> = (1900..2023).map { it.toString() }

            var Nombreusuario by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var texto by remember { mutableStateOf("") }
            var coincidencia by remember { mutableStateOf(false) }

            Rellenar(dato = "Nombre");
            Rellenar(dato = "Apellido")
            Nombreusuario = Rellenar(dato = "@Usuario")
            Text(text = "Fecha de Nacimiento")
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Desplegable(options = dia)
                Text(text = "/")
                Desplegable(options = mes)
                Text(text = "/")
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
                Text(text = "registrarse")

            }

            Text(
                text = texto,
                style = LocalTextStyle.current.copy(
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                ),
                modifier = Modifier.padding(16.dp)
            )
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
    modifier: Modifier=Modifier
){
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }
    var ret by remember { mutableStateOf("")}

    Box(
        modifier = modifier
    ) {
        Text(
            text = "${options[selectedIndex]}",
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

