package com.example.myapplicationt.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplicationt.data.models.ToDoData
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [ToDoData::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class ToDoDatabase: RoomDatabase() {

    abstract fun toDoDao(): ToDoDao

    companion object {

        @Volatile
        private var DATABASE_INSTANCE: ToDoDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context): ToDoDatabase {
            val tempInstance = DATABASE_INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ToDoDatabase::class.java,
                    "todo_database"
                ).build()
                DATABASE_INSTANCE = instance
                return instance
            }
        }
    }
}