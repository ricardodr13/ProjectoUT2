package com.example.projectout2

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import com.example.projectout2.ui.theme.ProjectoUT2Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(vm: UsuarioViewModel,navController: NavController) {
    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var login by remember { mutableStateOf(false) }
    Column(modifier = Modifier
        .padding(16.dp)
        .width(250.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.Titulo_app),
            modifier = Modifier.padding(top = 16.dp),
            style = MaterialTheme.typography.headlineLarge
        )

        Card( modifier = Modifier
            .height(250.dp)
            .width(250.dp)
        ){
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "imagen",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.Crop
        )
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
        ) {

            Text(
                text = stringResource(id = R.string.TextField_Email),
                Modifier.padding(top= 32.dp),
                style = MaterialTheme.typography.labelMedium
            )
            //OnvalueChange aprovechado para cambiar el estado del login a false para quitar el mensaje.
            OutlinedTextField(
                value = user,
                onValueChange = { user = it
                                login = false},
                label = { Text(stringResource(id = R.string.TextField_Email)) },
                modifier = Modifier
                    .fillMaxWidth()
            )

            Text(
                text = stringResource(id = R.string.TextField_Password),
                Modifier.padding(top= 16.dp),
                style = MaterialTheme.typography.labelMedium
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it
                    login = false},
                label = { Text(stringResource(id = R.string.TextField_Password)) },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text("¿No tienes Cuenta? Registrate.", modifier = Modifier.clickable { navController.navigate("Registro") })
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)) {
            Button(
                onClick = {
                    vm.obtenerUsuario(Comprobar(user,password))
                    if(vm.nombre!=""){
                        navController.navigate("Programa")
                    }else login = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text("Log in")
            }
            //Texto de usuario y contraseña incorrectos al introducir mal los campos.
            if (login) {
                Text(
                    text = "Usuario y contraseña incorrectos.",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 50.dp),
                    color = Color.Red,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}