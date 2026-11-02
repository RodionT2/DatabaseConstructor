package com.example.databaseconstructor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.databaseconstructor.ui.theme.DatabaseConstructorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DatabaseConstructorTheme {
                DatabaseHomeScreen()
            }
        }
    }
}

@Composable
fun DatabaseHomeScreen() {
    val databases = remember { mutableStateListOf<String>() }
    var showDialog by remember { mutableStateOf(false)}
    var databaseName by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp)
        ) {
            Text(
                text = "Мои базы данных",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { showDialog = true },
                modifier = Modifier.fillMaxSize()
            ) {
                Text("Создать базу данных")
            }

            Spacer(modifier = Modifier.height(20.dp))


        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DatabaseConstructorTheme {
        Greeting("Android")
    }
}
*/