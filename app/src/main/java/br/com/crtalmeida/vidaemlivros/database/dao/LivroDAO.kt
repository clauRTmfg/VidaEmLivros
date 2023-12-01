package br.com.crtalmeida.vidaemlivros.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.sqlite.db.SupportSQLiteQuery
import br.com.crtalmeida.vidaemlivros.database.model.Livro
import kotlinx.coroutines.flow.Flow

@Dao
interface LivroDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insere(livro: Livro)

    @RawQuery(observedEntities = arrayOf(Livro::class))
    fun buscaLivros(query: SupportSQLiteQuery): Flow<List<Livro>>

    @Query("SELECT * FROM Livro WHERE adquirido = 1")
    fun buscaMeusLivros(): Flow<List<Livro>>

    @Query("SELECT * FROM Livro WHERE adquirido = 0")
    fun buscaListaDesejos(): Flow<List<Livro>>

    @Query("SELECT * FROM Livro WHERE id = :id")
    fun buscaPorId(id: Long): Flow<Livro?>

    @Query("DELETE FROM Livro WHERE id = :id")
    suspend fun deleta(id: Long)

//    @Query("SELECT * FROM Contato WHERE idUsuario = :usuarioAtual")
//    fun buscaTodosPorUsuario(usuarioAtual: String): Flow<List<Contato>>

    @Query("SELECT * FROM Livro WHERE (nome LIKE :valorBusca||'%' OR autor LIKE :valorBusca||'%') AND adquirido = :adquirido")
    fun buscaPorNomeEAutor(valorBusca: String, adquirido: Long): Flow<List<Livro>>

}