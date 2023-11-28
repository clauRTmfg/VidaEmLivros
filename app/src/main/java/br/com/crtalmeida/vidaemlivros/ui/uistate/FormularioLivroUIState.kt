package br.com.crtalmeida.vidaemlivros.ui.uistate

import androidx.annotation.StringRes
import br.com.crtalmeida.vidaemlivros.R
import br.com.crtalmeida.vidaemlivros.util.Categoria
import br.com.crtalmeida.vidaemlivros.util.Formato
import java.time.LocalDate

data class FormularioLivroUIState(
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
    val onNomeMudou: (String) -> Unit = {},
    val onAutorMudou: (String) -> Unit = {},
    val onEditoraMudou: (String) -> Unit = {},
    val onImagemMudou: (String) -> Unit = {},
    val onFormatoMudou: (String) -> Unit = {},
    val onAdquiridoMudou: (Boolean) -> Unit = {},
    val onLeiturasMudou: (String) -> Unit = {},
    val onInicioLeituraMudou: (LocalDate) -> Unit = {},
    val onFimLeituraMudou: (LocalDate) -> Unit = {},
    val onDetalhesMudou: (String) -> Unit = {},
    val onCategoriaMudou: (String) -> Unit = {},
    val onMostrarCaixaDialogoImagem: (mostrar: Boolean) -> Unit = {},
    val onMostrarCaixaDataInicio: (mostrar: Boolean) -> Unit = {},
    val onMostrarCaixaDataFim: (mostrar: Boolean) -> Unit = {},
    val mostrarCaixaDataInicio: Boolean = false,
    val mostrarCaixaDataFim: Boolean = false,
    val mostrarCaixaDialogoImagem: Boolean = false,
    val dataInicioTexto: String = "",
    val dataFimTexto: String = "",
    @StringRes val tituloAppbar: Int? = R.string.titulo_cadastro_livro,
)