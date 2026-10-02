package com.example.column_application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.column_application.ui.theme.Column_applicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column_applicationTheme {
                MiVistaPrevia()
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun MiVistaPrevia() {
    Column_applicationTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFBBDEFB)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Elemento 1",
                fontSize = 32.sp,
                color = Color.Blue
            )

            Spacer(modifier = Modifier.height(24.dp))
            Spacer(modifier = Modifier.height(24.dp))

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                item { Text(text = "Java", fontSize = 28.sp) }
                item { Text(text = "Kotlin", fontSize = 28.sp) }
                item { Text(text = "Swift", fontSize = 28.sp) }
                item { Text(text = "Dart", fontSize = 28.sp) }
                item { Text(text = "Python", fontSize = 28.sp) }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Elemento 2",
                fontSize = 32.sp,
                color = Color.Red
            )

            Spacer(modifier = Modifier.height(24.dp))

            Image(
                painter = painterResource(id = R.drawable.img),                contentDescription = "Imagen de ejemplo",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Elemento 3",
                fontSize = 32.sp,
                color = Color.Magenta
            )
        }
    }
}