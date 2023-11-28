package br.com.crtalmeida.vidaemlivros.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun LocalDate.converteParaString(): String {
    val formatter = DateTimeFormatter.ofPattern(ddMMyyyy)
    return this.format(formatter)
}

fun String.tipoFormato(): Formato {
    return when(this) {
        "Capa comum" -> Formato.CAPA_COMUM
        "Capa dura" -> Formato.CAPA_DURA
        "eBook" -> Formato.EBOOK
        "PDF" -> Formato.PDF
        else -> Formato.OUTRO
    }
}

fun String.tipoCategoria(): Categoria {
    return when(this) {
        "Espírita" -> Categoria.ESPIRITA
        "Ficção" -> Categoria.FICCAO
        "Artes" -> Categoria.ARTES
        "Ciências" -> Categoria.CIENCIAS
        else -> Categoria.OUTRA
    }
}

