package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TrackedItemEntity::class], version = 1, exportSchema = false)
abstract class BioDatabase : RoomDatabase() {
    abstract fun trackedItemDao(): TrackedItemDao

    companion object {
        @Volatile
        private var INSTANCE: BioDatabase? = null

        fun getDatabase(context: Context): BioDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BioDatabase::class.java,
                    "biopulse_database"
                ).fallbackToDestructiveMigration(dropAllTables = true).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
