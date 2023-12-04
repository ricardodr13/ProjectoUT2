package com.example.projectout2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.projectout2.ui.theme.ProjectoUT2Theme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//pasar el navhost como parametro adicional
fun DataChangeScreen(vm: UsuarioViewModel,bottonPadding: PaddingValues) {
    var nombre by remember { mutableStateOf(vm.nombre) }
    var apellidos by remember { mutableStateOf(vm.apellido) }
    var usuario by remember { mutableStateOf(vm.nombreUsuario) }
    var fechaDeNacimiento by remember { mutableStateOf(vm.fechaNacimiento.toString()) }

    //Creacion del snackbarHost
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    SnackbarHost(hostState = snackbarHostState,Modifier.wrapContentHeight(Alignment.Bottom))


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top= 10.dp, bottom = bottonPadding.calculateBottomPadding()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.TextField_Nombre),
                    Modifier.padding(top = 16.dp),
                    style = MaterialTheme.typography.labelMedium
                )

                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text(stringResource(id = R.string.TextField_Nombre)) },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.TextField_Apellidos),
                    Modifier.padding(top = 16.dp),
                    style = MaterialTheme.typography.labelMedium
                )

                OutlinedTextField(
                    value = apellidos,
                    onValueChange = { apellidos = it },
                    label = { Text(stringResource(id = R.string.TextField_Apellidos)) },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.TextField_UserName),
                    Modifier.padding(top = 16.dp),
                    style = MaterialTheme.typography.labelMedium
                )

                OutlinedTextField(
                    value = usuario,
                    onValueChange = { usuario = it },
                    label = { Text(stringResource(id = R.string.TextField_UserName)) },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.TextField_Fecha),
                    Modifier.padding(top = 16.dp),
                    style = MaterialTheme.typography.labelMedium
                )

                OutlinedTextField(
                    value = fechaDeNacimiento,
                    onValueChange = { fechaDeNacimiento = it },
                    label = { Text(stringResource(id = R.string.TextField_Fecha)) },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp)
            ) {
                Button(
                    onClick = {
                        // lanzamos el snackbarHost con una duracion corta(por defecto)
                        scope.launch {
                            snackbarHostState.showSnackbar("Datos Guardados")
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text("Guardar Cambios")
                }
            }
        }
    }