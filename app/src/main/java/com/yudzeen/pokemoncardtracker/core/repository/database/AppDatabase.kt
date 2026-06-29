package com.yudzeen.pokemoncardtracker.core.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yudzeen.pokemoncardtracker.core.repository.database.dao.PokemonCardDao
import com.yudzeen.pokemoncardtracker.core.repository.database.entity.PokemonCardEntity
import com.yudzeen.pokemoncardtracker.core.repository.database.util.Converters

@Database(entities = [PokemonCardEntity::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun pokemonCardDao(): PokemonCardDao

    companion object {
        const val DATABASE_NAME = "pokemon-card-tracker-db"

        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }
}

