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
    val context = androidx.compose.ui.platform.LocalContext.current
    val catalog = remember { DatabaseCatalogHelper(context) }

    var databases by remember {
        mutableStateOf(catalog.getAllDatabases())
    }
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
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Создать базу данных")
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (databases.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("У вас пока нет баз данных")
                }
            } else {
                LazyColumn {
                    items(databases) { database ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 5.dp)
                        ) {
                            Text(
                                text = database.name,
                                modifier = Modifier.padding(20.dp)
                            )
                        }
                    }
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },
            title = {
                Text("Новая база данных")
            },
            text = {
                OutlinedTextField(
                    value = databaseName,
                    onValueChange = { databaseName = it },
                    label = { Text("Название базы")},
                    singleLine = true
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (databaseName.isNotBlank()) {
                            catalog.addDatabase(databaseName.trim())
                            databases = catalog.getAllDatabases()
                            databaseName = ""
                            showDialog = false
                        }
                    }
                ) {
                    Text("Создать")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text("Отмена")
                }
            }
        )
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