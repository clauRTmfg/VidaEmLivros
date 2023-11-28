package br.com.crtalmeida.vidaemlivros.util

sealed class Formato(val texto: String) {
    object CAPA_COMUM : Formato("Capa comum")
    object CAPA_DURA : Formato("Capa dura")
    object EBOOK : Formato("eBook")
    object PDF : Formato("PDF")
    object OUTRO : Formato("Outro")
}

val opcoesFormatos = listOf(
    Formato.CAPA_COMUM.texto,
    Formato.CAPA_DURA.texto,
    Formato.EBOOK.texto,
    Formato.PDF.texto,
    Formato.OUTRO.texto
)