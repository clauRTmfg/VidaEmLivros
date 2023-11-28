package br.com.crtalmeida.vidaemlivros.util

sealed class Categoria(val texto: String) {
    object ESPIRITA : Categoria("Espírita")
    object FICCAO : Categoria("Ficção")
    object ARTES : Categoria("Artes")
    object CIENCIAS : Categoria("Ciências")
    object OUTRA : Categoria("Outra")
}

val opcoesCategorias = listOf(
    Categoria.OUTRA.texto,
    Categoria.ESPIRITA.texto,
    Categoria.FICCAO.texto,
    Categoria.ARTES.texto,
    Categoria.CIENCIAS.texto
)