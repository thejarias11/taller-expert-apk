package com.example.tallerxpert

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RegisterScreen(onRegisterComplete: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(16.dp)) {
            Text("Registro TallerXpert", style = MaterialTheme.typography.headlineLarge)

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Nombre") })
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Correo") })
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Contraseña") })
            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = { onRegisterComplete() }) {
                Text("Registrarse")
            }
        }
    }
}
