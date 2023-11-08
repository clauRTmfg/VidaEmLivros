package br.com.crtalmeida.vidaemlivros.ui.uistate

import br.com.crtalmeida.vidaemlivros.database.model.Livro

data class ListaDesejosUIState(
    val livros: List<Livro> = emptyList()
)