package com.ajdev.getitdone.data.local.converter

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.LocalDate

class Converter {

    @TypeConverter
    fun fromLocalDate(date: LocalDate): String = date.toString()

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toLocalDate(dateString: String): LocalDate = LocalDate.parse(dateString)
}