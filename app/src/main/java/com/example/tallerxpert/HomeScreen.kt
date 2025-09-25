package com.example.tallerxpert

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen() {
    var selectedItem by remember { mutableStateOf(0) }

    val items = listOf("Citas", "Inventario", "Perfil")
    val icons = listOf(Icons.Filled.CalendarToday, Icons.Filled.Build, Icons.Filled.Person)

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(icons[index], contentDescription = item) },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
        }
    ) { innerPadding ->
        when (selectedItem) {
            0 -> AppointmentsScreen()
            1 -> InventoryScreen()
            2 -> ProfileScreen()
        }
    }
}
