package com.example.megasenakotlin

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NumbersDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "numbers_db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NUMBERS = "numbers"
        private const val COLUMN_ID = "id"
        private const val COLUMN_N1 = "n1"
        private const val COLUMN_N2 = "n2"
        private const val COLUMN_N3 = "n3"
        private const val COLUMN_N4 = "n4"
        private const val COLUMN_N5 = "n5"
        private const val COLUMN_N6 = "n6"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val sqlCreateEntries = """
            CREATE TABLE $TABLE_NUMBERS (
                $COLUMN_ID INTEGER PRIMARY KEY,
                $COLUMN_N1 INTEGER,
                $COLUMN_N2 INTEGER,
                $COLUMN_N3 INTEGER,
                $COLUMN_N4 INTEGER,
                $COLUMN_N5 INTEGER,
                $COLUMN_N6 INTEGER
            )
        """.trimIndent()
        db.execSQL(sqlCreateEntries)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NUMBERS")
        onCreate(db)
    }

    fun insertNumbers(numbers: List<Int>) {
        writableDatabase.use { db ->
            val values = ContentValues().apply {
                put(COLUMN_N1, numbers[0])
                put(COLUMN_N2, numbers[1])
                put(COLUMN_N3, numbers[2])
                put(COLUMN_N4, numbers[3])
                put(COLUMN_N5, numbers[4])
                put(COLUMN_N6, numbers[5])
            }
            db.insert(TABLE_NUMBERS, null, values)
        }
    }

    fun retrieveAllNumbers(): List<List<Int>> {
        val numbersList = mutableListOf<List<Int>>()
        val selectQuery = "SELECT * FROM $TABLE_NUMBERS"
        readableDatabase.use { db ->
            db.rawQuery(selectQuery, null).use { cursor ->
                while (cursor.moveToNext()) {
                    val numbers = listOf(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_N1)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_N2)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_N3)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_N4)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_N5)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_N6))
                    )
                    numbersList.add(numbers)
                }
            }
        }
        return numbersList
    }
}
