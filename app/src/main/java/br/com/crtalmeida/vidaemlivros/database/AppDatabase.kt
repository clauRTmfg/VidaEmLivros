package br.com.crtalmeida.vidaemlivros.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.crtalmeida.vidaemlivros.database.converters.Converters
import br.com.crtalmeida.vidaemlivros.database.dao.LivroDAO
import br.com.crtalmeida.vidaemlivros.database.model.Livro

@Database(
    entities = [Livro::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun livroDAO(): LivroDAO

}