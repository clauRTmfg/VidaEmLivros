package br.com.crtalmeida.vidaemlivros.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.crtalmeida.vidaemlivros.util.Categoria
import br.com.crtalmeida.vidaemlivros.util.Formato
import java.time.LocalDate

@Entity
data class Livro(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val nome: String = "",
    val autor: String = "",
    val editora: String = "",
    val imagem: String = "",
    val formato: String = Formato.OUTRO.texto,
    val adquirido: Boolean = false,
    val leituras: Int = 0,
    val inicioLeitura: LocalDate? = null,
    val fimLeitura: LocalDate? = null,
    val detalhes: String = "",
    val categoria: String = Categoria.OUTRA.texto
)



