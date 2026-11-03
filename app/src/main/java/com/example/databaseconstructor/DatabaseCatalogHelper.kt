package com.example.databaseconstructor

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.UUID

data class DatabaseInfo(
    val id: String,
    val name: String,
    val fileName: String
)

class DatabaseCatalogHelper (context: Context) :
    SQLiteOpenHelper(context, "catalog.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """
               CREATE TABLE app_databases (
                id TEXT PRIMARY KEY,
                name TEXT NOT NULL,
                file_name TEXT NOT NULL UNIQUE
            ) 
            """.trimIndent()
        )
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        // Механизм обновления структуры добавим при необходимости.
    }

    fun getAllDatabases(): List<DatabaseInfo> {
        val result = mutableListOf<DatabaseInfo>()

        val cursor = readableDatabase.rawQuery(
            "SELECT id, name, file_name FROM app_databases",
            null
        )

        cursor.use {
            while (it.moveToNext()) {
                result.add(
                    DatabaseInfo(
                        id = it.getString(0),
                        name = it.getString(1),
                        fileName = it.getString(2)
                    )
                )
            }
        }

        return result

    }

    fun addDatabase(name: String): DatabaseInfo {
        val database = DatabaseInfo(
            id = UUID.randomUUID().toString(),
            name = name,
            fileName = "database_${UUID.randomUUID()}.db"
        )

        val values = ContentValues().apply {
            put("id", database.id)
            put("name", database.name)
            put("file_name", database.fileName)
        }

        writableDatabase.insertOrThrow(
            "app_databases",
            null,
            values
    )

    return database

    }
}