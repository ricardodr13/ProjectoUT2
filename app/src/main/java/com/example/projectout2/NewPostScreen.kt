package com.example.projectout2

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewPostScreen(navegacion: NavController, bottomPadding: PaddingValues) {

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        navegacion.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "")
                    }
                    Text(
                        text = "Nueva publicacion", modifier = Modifier.padding(10.dp, 0.dp)
                    )
                }

            }, colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary
            )
        )
    }) { innerPadding ->

        var titulo by remember { mutableStateOf("") }
        var descripcion by remember { mutableStateOf("") }
        var uri by remember { mutableStateOf<Uri?>(null) }
        val singlePhotoPicker =
            rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia(),
                onResult = {
                    uri = it
                })

        LazyColumn(
            Modifier.padding(
                top = innerPadding.calculateTopPadding(),
                bottom = bottomPadding.calculateBottomPadding()
            )
        ) {
            item {
                Text(
                    text = stringResource(id = R.string.TextField_TituloPublicacion),
                    Modifier.padding(top = 32.dp),
                    style = MaterialTheme.typography.labelMedium
                )

                OutlinedTextField(value = titulo,
                    onValueChange = { titulo = it },
                    label = { Text(text = stringResource(id = R.string.TextField_TituloPublicacion)) },
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = stringResource(id = R.string.TextField_DescripcionPublicacion),
                    Modifier.padding(top = 16.dp),
                    style = MaterialTheme.typography.labelMedium
                )

                OutlinedTextField(value = descripcion,
                    onValueChange = { descripcion = it },
                    label = { Text(stringResource(id = R.string.TextField_DescripcionPublicacion)) },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {

                        singlePhotoPicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))

                    }, modifier = Modifier.fillMaxWidth()

                ) {
                    Text(stringResource(id = R.string.Button_SubirFoto))
                }

                AsyncImage(
                    model = uri, contentDescription = null, Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        navegacion.popBackStack()
                    }, modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text(stringResource(id = R.string.Button_PublicarFoto))
                }
            }
        }
    }
}