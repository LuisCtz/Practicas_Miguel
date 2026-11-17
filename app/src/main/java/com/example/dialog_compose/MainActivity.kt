package com.example.dialog_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DialogComposeTheme {
                ViewContainer()
            }
        }
    }
}

@Composable
fun DialogComposeTheme(content: @Composable () -> Unit) {
    MaterialTheme(content = content)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar() {
    TopAppBar(
        title = { Text(text = "Perfil en Scaffold") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF6366F1),
            titleContentColor = Color.White
        )
    )
}

@Composable
fun FAB(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = Color(0xFFEC407A)
    ) {
        Text(text = "+", fontSize = MaterialTheme.typography.titleLarge.fontSize)
    }
}

@Composable
fun MyDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (show) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(text = "Suscríbete") },
            text = { Text(text = "¿Quieres suscribirte a nuestro canal?") },
            confirmButton = {
                TextButton(onClick = onConfirm) {
                    Text(text = "ACEPTAR")
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text(text = "CANCELAR")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ViewContainer() {
    var contadorBoton by remember { mutableIntStateOf(0) }
    var mostrarDialogo by remember { mutableStateOf(false) }
    var mensaje by remember { mutableStateOf("") }

    MyDialog(
        show = mostrarDialogo,
        onDismiss = {
            mostrarDialogo = false
            mensaje = "Seleccionaste: CANCELAR"
        },
        onConfirm = {
            mostrarDialogo = false
            mensaje = "¡Gracias por suscribirte!"
        }
    )

    Scaffold(
        topBar = { Toolbar() },
        floatingActionButton = { FAB(onClick = { contadorBoton++ }) },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        Content(
            modifier = Modifier.padding(paddingValues),
            contadorBoton = contadorBoton,
            onMostrarDialogo = { mostrarDialogo = true },
            mensaje = mensaje
        )
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    contadorBoton: Int = 0,
    onMostrarDialogo: () -> Unit,
    mensaje: String
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFEDE7F6)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Image(
            painter = painterResource(id = R.drawable.imagen_perfil),
            contentDescription = "Imagen de perfil",
            modifier = Modifier
                .size(180.dp)
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "Luis Armando Cervantes Tellez",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4A148C)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Estudiante de Ingeniería • Desarrollo Móvil",
            fontSize = 16.sp,
            color = Color(0xFF6A1B9A)
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            item { Text(text = "Java", fontSize = 20.sp, color = Color(0xFF5C6BC0)) }
            item { Text(text = "Kotlin", fontSize = 20.sp, color = Color(0xFF5C6BC0)) }
            item { Text(text = "Swift", fontSize = 20.sp, color = Color(0xFF5C6BC0)) }
            item { Text(text = "Dart", fontSize = 20.sp, color = Color(0xFF5C6BC0)) }
            item { Text(text = "Python", fontSize = 20.sp, color = Color(0xFF5C6BC0)) }
        }

        Spacer(modifier = Modifier.height(32.dp))

        var contadorCorazon by remember { mutableIntStateOf(0) }
        Row(
            modifier = Modifier
                .padding(8.dp)
                .clickable { contadorCorazon++ },
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "❤️", fontSize = 28.sp)
            Text(
                text = "$contadorCorazon",
                fontSize = 22.sp,
                color = Color(0xFF4A148C),
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Botón presionado: $contadorBoton",
            fontSize = 18.sp,
            color = Color(0xFF6A1B9A)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = mensaje,
            fontSize = 18.sp,
            color = Color(0xFF4A148C),
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onMostrarDialogo) {
            Text(text = "Mostrar Diálogo")
        }
    }
}
