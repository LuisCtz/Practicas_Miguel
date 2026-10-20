package com.example.column_application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.column_application.ui.theme.Column_applicationTheme

// Importaciones para Google Maps
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.android.gms.maps.model.LatLng
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column_applicationTheme {
                ViewContainer()
            }
        }
    }
}

// ==========================================
// BARRA SUPERIOR - Lavanda
// ==========================================
@Composable
fun Toolbar() {
    TopAppBar(
        title = {
            Text(text = "Perfil en Scaffold")
        },
        backgroundColor = Color(0xFF6366F1),
        contentColor = Color.White
    )
}

// ==========================================
// BOTÓN FLOTANTE - Rosa suave
// ==========================================
@Composable
fun FAB(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        backgroundColor = Color(0xFFEC407A)
    ) {
        Text(text = "+", fontSize = 24.sp)
    }
}

// ==========================================
// ESTRUCTURA PRINCIPAL
// ==========================================
@Preview(showBackground = true)
@Composable
fun ViewContainer() {
    var contadorBoton by rememberSaveable { mutableStateOf(0) }

    Scaffold(
        topBar = { Toolbar() },
        floatingActionButton = { FAB(onClick = { contadorBoton++ }) },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        Content(
            modifier = Modifier.padding(paddingValues),
            contadorBoton = contadorBoton
        )
    }
}

// ==========================================
// CONTENIDO CON COLORES MEJORADOS
// ==========================================
@Composable
fun Content(
    modifier: Modifier = Modifier,
    contadorBoton: Int = 0
) {
    Column_applicationTheme {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color(0xFFEDE7F6)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // 1. IMAGEN PRINCIPAL
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "Imagen de perfil",
                modifier = Modifier
                    .size(180.dp)
                    .padding(bottom = 16.dp),
                contentScale = ContentScale.Crop
            )

            // 2. NOMBRE
            Text(
                text = "Luis Armando Cervantes Tellez",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4A148C)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 3. DESCRIPCIÓN
            Text(
                text = "Estudiante de Ingeniería • Desarrollo Móvil",
                fontSize = 16.sp,
                color = Color(0xFF6A1B9A)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 4. FILA DE LENGUAJES
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

            // 5. CONTADOR DE CORAZÓN
            var contadorCorazon by rememberSaveable { mutableStateOf(0) }
            Row(
                modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Me gusta",
                    tint = Color(0xFFE91E63),
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { contadorCorazon++ }
                )
                Text(
                    text = "$contadorCorazon",
                    fontSize = 22.sp,
                    color = Color(0xFF4A148C),
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 6. CONTADOR DEL BOTÓN FLOTANTE
            Text(
                text = "Botón presionado: $contadorBoton",
                fontSize = 18.sp,
                color = Color(0xFF6A1B9A)
            )
        }
    }
}

// ==========================================
// MAPA DE GOOGLE
// ==========================================
@Composable
fun MyGoogleMaps() {
    val posicion1 = LatLng(19.7837, -98.0098)  // Tepeapulco
    val posicion2 = LatLng(19.7900, -98.0150)  // Punto Norte
    val posicion3 = LatLng(19.7750, -97.9950)  // Punto Sur

    val camara = rememberCameraPositionState {
        position = com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(
            posicion1,
            13f
        )
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = camara,
        properties = com.google.maps.android.compose.MapProperties(
            mapType = com.google.maps.android.compose.MapType.NORMAL
        ),
        uiSettings = com.google.maps.android.compose.MapUiSettings(
            zoomControlsEnabled = false
        )
    ) {
        Marker(
            position = posicion1,
            title = "Tepeapulco, Hidalgo",
            snippet = "Ubicación principal"
        )
        Marker(
            position = posicion2,
            title = "Punto de Referencia 1",
            snippet = "Zona Norte"
        )
        Marker(
            position = posicion3,
            title = "Punto de Referencia 2",
            snippet = "Zona Sur"
        )
    }
}