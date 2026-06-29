package com.yudzeen.pokemoncardtracker.core.repository.database.util

import android.net.Uri
import androidx.room.TypeConverter
import com.yudzeen.pokemoncardtracker.core.model.Category
import com.yudzeen.pokemoncardtracker.core.model.Series
import java.util.UUID
import androidx.core.net.toUri

object Converters {

    @TypeConverter
    fun fromUuid(uuid: UUID?): String? {
        return uuid?.toString()
    }

    @TypeConverter
    fun stringToUuid(value: String?): UUID? {
        return value?.let { UUID.fromString(it) }
    }

    @TypeConverter
    fun fromSeries(series: Series?): String? {
        return series?.toString()
    }

    @TypeConverter
    fun stringToSeries(value: String?): Series? {
        return value?.let { Series.valueOf(it) }
    }

    @TypeConverter
    fun fromExpansion(expansion: Series.Expansion?): String? {
        return expansion?.toString()
    }

    @TypeConverter
    fun stringToExpansion(value: String?): Series.Expansion? {
        return value?.let { Series.Expansion.valueOf(it) }
    }

    @TypeConverter
    fun fromCategory(category: Category?): String? {
        return category?.toString()
    }

    @TypeConverter
    fun stringToCategory(value: String?): Category? {
        return value?.let { Category.valueOf(it) }
    }

    @TypeConverter
    fun fromUri(uri: Uri?): String? {
        return uri?.toString()
    }

    @TypeConverter
    fun stringToUri(value: String?): Uri? {
        return value?.toUri()
    }
}