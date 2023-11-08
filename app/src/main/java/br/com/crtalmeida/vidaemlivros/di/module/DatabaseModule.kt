package br.com.crtalmeida.vidaemlivros.di.module

import android.content.Context
import androidx.room.Room
import br.com.crtalmeida.vidaemlivros.database.AppDatabase
import br.com.crtalmeida.vidaemlivros.database.dao.LivroDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATABASE_NAME = "vidaEmLivros.db"

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideLivroDAO(db: AppDatabase): LivroDAO {
        return db.livroDAO()
    }

}