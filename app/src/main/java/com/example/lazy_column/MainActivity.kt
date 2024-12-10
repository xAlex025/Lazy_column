package com.example.lazy_column

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PantallaTareas()
        }
    }
}

@Composable
fun PantallaTareas() {
    val tareas = remember {
        mutableStateListOf(
            Tarea("Tarea 1: Comprar alimentos", false),
            Tarea("Tarea 2: Llamar a Juan", false),
            Tarea("Tarea 3: Preparar la presentación", true),
            Tarea("Tarea 4: Ir al gimnasio", false),
            Tarea("Tarea 5: Lavar el coche", false),
            Tarea("Tarea 6: Leer un capítulo de un libro", true),
            Tarea("Tarea 7: Limpiar la casa", false),
            Tarea("Tarea 8: Revisar correos electrónicos", true),
            Tarea("Tarea 9: Hacer la compra online", false),
            Tarea("Tarea 10: Organizar reunión con equipo", false),
            Tarea("Tarea 11: Visitar al médico", true),
            Tarea("Tarea 12: Planificar vacaciones", false),
            Tarea("Tarea 13: Estudiar para el examen", false),
            Tarea("Tarea 14: Ver película con amigos", true),
            Tarea("Tarea 15: Hacer backup del teléfono", false)
        )
    }

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .systemBarsPadding()
        .background(Color(0xFFF5F5F5))
    ) {
        items(tareas) { tarea ->
            ElementoTarea(
                tarea = tarea,
                alHacerClic = {
                    val indice = tareas.indexOf(tarea)
                    if (indice != -1) {
                        tareas[indice] = tarea.copy(completa = !tarea.completa)
                    }
                }
            )
        }
    }
}

@Composable
fun ElementoTarea(tarea: Tarea, alHacerClic: () -> Unit) {
    val colorFondo = if (tarea.completa) Color(0xFFDFF6DD) else Color(0xFFFFE4E1)
    val colorTexto = if (tarea.completa) Color(0xFF2E7D32) else Color(0xFFC62828)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(color = colorFondo, shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if (tarea.completa) Icons.Filled.CheckCircle else Icons.Filled.Warning,
            contentDescription = if (tarea.completa) "Tarea completada" else "Tarea pendiente",
            tint = colorTexto,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = tarea.titulo,
            color = colorTexto,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Button(
            onClick = alHacerClic,
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                containerColor = if (tarea.completa) Color(0xFF66BB6A) else Color(0xFFEF5350),
                contentColor = Color.White
            )
        ) {
            Text(text = if (tarea.completa) "Pendiente" else "Completada")
        }
    }
}

data class Tarea(val titulo: String, val completa: Boolean)
