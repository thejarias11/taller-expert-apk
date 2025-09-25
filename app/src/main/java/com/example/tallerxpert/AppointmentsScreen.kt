package com.example.tallerxpert

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Cita(
    val id: Int,
    val cliente: String,
    val fecha: String,
    val descripcion: String
)

@Composable
fun AppointmentsScreen() {
    var citas by remember { mutableStateOf(listOf<Cita>()) }
    var cliente by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("📅 Agendar Cita", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = cliente, onValueChange = { cliente = it }, label = { Text("Cliente") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = fecha, onValueChange = { fecha = it }, label = { Text("Fecha") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = descripcion, onValueChange = { descripcion = it }, label = { Text("Descripción") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (cliente.isNotBlank() && fecha.isNotBlank() && descripcion.isNotBlank()) {
                    val newCita = Cita(
                        id = citas.size + 1,
                        cliente = cliente,
                        fecha = fecha,
                        descripcion = descripcion
                    )
                    citas = citas + newCita
                    cliente = ""
                    fecha = ""
                    descripcion = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agendar cita")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(citas) { cita ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("👤 Cliente: ${cita.cliente}", style = MaterialTheme.typography.titleMedium)
                        Text("📅 Fecha: ${cita.fecha}")
                        Text("📝 ${cita.descripcion}")
                    }
                }
            }
        }
    }
}
