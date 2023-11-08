package br.com.crtalmeida.vidaemlivros.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Livro(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val nome: String,
    val autor: String,
    val editora: String? = null,
    val imagem: String? = null,
    val formato: String? = null,
    val adquirido: Boolean,
    val lido: Boolean,
    val inicioLeitura: String? = null,
    val fimLeitura: String? = null,
    val edicao: String? = null,
    val observacao: String? = null,
    val categoria: String? = null
)

sealed class Formato(val valor: String) {
    object CAPA_COMUM : Formato("Capa comum")
    object CAPA_DURA : Formato("Capa dura")
    object EBOOK : Formato("eBook")
    object PDF : Formato("PDF")
    object OUTRO : Formato("Outro")
}