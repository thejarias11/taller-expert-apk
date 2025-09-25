package com.example.tallerxpert

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InventoryScreen() {
    var repuestos by remember { mutableStateOf(listOf<Repuesto>()) }
    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var stock by remember { mutableStateOf("") }
    var valorSugerido by remember { mutableStateOf("") }
    var editingRepuesto: Repuesto? by remember { mutableStateOf(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("🛠 Inventario del Taller", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        // Campos
        OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = precio, onValueChange = { precio = it }, label = { Text("Precio") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = stock, onValueChange = { stock = it }, label = { Text("Stock") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = valorSugerido, onValueChange = { valorSugerido = it }, label = { Text("Valor sugerido") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))

        // Botón Guardar / Editar
        Button(
            onClick = {
                if (nombre.isNotBlank() && precio.isNotBlank() && stock.isNotBlank() && valorSugerido.isNotBlank()) {
                    if (editingRepuesto != null) {
                        // Editar
                        repuestos = repuestos.map {
                            if (it.id == editingRepuesto!!.id) {
                                it.copy(
                                    nombre = nombre,
                                    precio = precio.toDouble(),
                                    stock = stock.toInt(),
                                    valorSugerido = valorSugerido.toDouble()
                                )
                            } else it
                        }
                        editingRepuesto = null
                    } else {
                        // Agregar nuevo
                        val newRepuesto = Repuesto(
                            id = repuestos.size + 1,
                            nombre = nombre,
                            precio = precio.toDouble(),
                            stock = stock.toInt(),
                            valorSugerido = valorSugerido.toDouble()
                        )
                        repuestos = repuestos + newRepuesto
                    }
                    // Limpiar campos
                    nombre = ""
                    precio = ""
                    stock = ""
                    valorSugerido = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (editingRepuesto != null) "Actualizar repuesto" else "Agregar repuesto")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista
        LazyColumn {
            items(repuestos) { repuesto ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("🔧 ${repuesto.nombre}", style = MaterialTheme.typography.titleMedium)
                        Text("💲 Precio: ${repuesto.precio}")
                        Text("📦 Stock: ${repuesto.stock}")
                        Text("⭐ Valor sugerido: ${repuesto.valorSugerido}")

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            TextButton(onClick = {
                                nombre = repuesto.nombre
                                precio = repuesto.precio.toString()
                                stock = repuesto.stock.toString()
                                valorSugerido = repuesto.valorSugerido.toString()
                                editingRepuesto = repuesto
                            }) {
                                Text("Editar")
                            }
                            TextButton(onClick = {
                                repuestos = repuestos.filter { it.id != repuesto.id }
                            }) {
                                Text("Eliminar", color = MaterialTheme.colorScheme.error)
                            }
                        }
                    }
                }
            }
        }
    }
}
