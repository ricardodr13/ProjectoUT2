package com.example.projectout2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projectout2.ui.theme.ProjectoUT2Theme

@Composable
fun ProfileScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        //esta caja contiene la imagen de perfil del usuario y el boton para editarla
        Box(contentAlignment = Alignment.BottomEnd) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
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
                Icon(Icons.Filled.Edit, contentDescription = "a")
            }
        }

        //Esta columna contiene los datos del usuario (nombre y nombre de usuario)
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            //cambiar los textos por los datos del usuario.
            Text(
                "Ricardo Gómez",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineLarge
            )
            Text("@ricardodr13", style = MaterialTheme.typography.titleMedium)
        }

        //Esta Columna contiene los 2 botones para ir a cambiar los datos personales y el de seguridad.
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxWidth()
        ) {
            OutlinedButton(
                onClick = { /*TODO*/ },
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
                        Icons.Filled.KeyboardArrowRight, contentDescription = "a",tint = Color.Black
                    )
                }
            }
            OutlinedButton(
                onClick = { /*TODO*/ },
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
                    Text("Seguridad",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Icon(Icons.Filled.KeyboardArrowRight,
                        contentDescription = "a",
                        tint = Color.Black
                    )
                }
            }
        }

        Button(
            onClick = {
                // Volver a la pantalla de login
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
        ) {
            Text(text = "Log out")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProjectoUT2Theme {
        ProfileScreen()
    }
}