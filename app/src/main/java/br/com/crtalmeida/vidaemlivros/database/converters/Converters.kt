package br.com.crtalmeida.vidaemlivros.database.converters

import androidx.room.TypeConverter
import java.time.LocalDate
import java.util.Date

class Converters {
    @TypeConverter
    fun dateParaString(valor: LocalDate?): String?{
        return valor?.toString()
    }

    @TypeConverter
    fun stringParaDate(valor: String?): LocalDate?{
        return valor?.let { LocalDate.parse(it) }
    }

}