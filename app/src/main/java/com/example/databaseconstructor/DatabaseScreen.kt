// Интерфейс БД

package com.example.databaseconstructor

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DatabaseScreen(
    database: DatabaseInfo,
    onBack: () -> Unit // Использование другой функции в качестве параметра
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text(
            text = database.name,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { }) {
            Text("Создать таблицу")
        }

        Spacer(modifier = Modifier.height(16.dp))

                    // onBack = {selectedDatabase = null} см. Main 108
        Button(onClick = onBack) {
            Text("Назад")
        }
    }
}