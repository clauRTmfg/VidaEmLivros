package br.com.crtalmeida.vidaemlivros.ui.uistate

import br.com.crtalmeida.vidaemlivros.util.Categoria
import br.com.crtalmeida.vidaemlivros.util.Formato
import br.com.crtalmeida.vidaemlivros.util.StatusLivro
import java.time.LocalDate

data class DetalhesLivroUIState (
    val id: Long = 0L,
    val nome: String = "",
    val autor: String = "",
    val editora: String = "",
    val imagem: String = "",
    val formato: Formato = Formato.OUTRO,
    val adquirido: Boolean = true,
    val leituras: Int = 0,
    val inicioLeitura: LocalDate? = null,
    val fimLeitura: LocalDate? = null,
    val detalhes: String = "",
    val categoria: Categoria = Categoria.OUTRA,
    val status: StatusLivro = StatusLivro.NAO_LIDO,
    val onStatusMudou: () -> Unit = {},
    val mostraDialogExclusao: Boolean = false,
    val onMostraDialogExclusaoMudou: (Boolean) -> Unit = {},
    val textoBotao: String = StatusLivro.NAO_LIDO.texto
)