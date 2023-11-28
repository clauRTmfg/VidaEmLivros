package br.com.crtalmeida.vidaemlivros.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.crtalmeida.vidaemlivros.R
import br.com.crtalmeida.vidaemlivros.database.dao.LivroDAO
import br.com.crtalmeida.vidaemlivros.database.model.Livro
import br.com.crtalmeida.vidaemlivros.ui.uistate.FormularioLivroUIState
import br.com.crtalmeida.vidaemlivros.util.ID_LIVRO
import br.com.crtalmeida.vidaemlivros.util.converteParaString
import br.com.crtalmeida.vidaemlivros.util.tipoCategoria
import br.com.crtalmeida.vidaemlivros.util.tipoFormato
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormularioLivroViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val dao: LivroDAO,
) : ViewModel() {

    private val idLivro = savedStateHandle.get<Long>(ID_LIVRO)

    private val _uiState = MutableStateFlow(FormularioLivroUIState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            carregaLivro()
        }

        _uiState.update { state ->
            state.copy(onNomeMudou = {
                _uiState.value = _uiState.value.copy(
                    nome = it
                )
            }, onAutorMudou = {
                _uiState.value = _uiState.value.copy(
                    autor = it
                )
            }, onEditoraMudou = {
                _uiState.value = _uiState.value.copy(
                    editora = it
                )
            }, onImagemMudou = {
                _uiState.value = _uiState.value.copy(
                    imagem = it
                )
            }, onFormatoMudou = {
                _uiState.value = _uiState.value.copy(
                    formato = it.tipoFormato()
                )
            }, onAdquiridoMudou = {
                _uiState.value = _uiState.value.copy(
                    adquirido = !(it)
                )
            }, onLeiturasMudou = {
                _uiState.value = _uiState.value.copy(
                    leituras = it.toInt()
                )
            }, onInicioLeituraMudou = {
                _uiState.value = _uiState.value.copy(
                    inicioLeitura = it, mostrarCaixaDataInicio = false
                    //inicioLeitura = it.converteParaDate(), mostrarCaixaDataInicio = false
                )
            }, onFimLeituraMudou = {
                _uiState.value = _uiState.value.copy(
                    fimLeitura = it, mostrarCaixaDataFim = false
                    //fimLeitura = it.converteParaDate(), mostrarCaixaDataFim = false
                )
            }, onDetalhesMudou = {
                _uiState.value = _uiState.value.copy(
                    detalhes = it
                )
            }, onCategoriaMudou = {
                _uiState.value = _uiState.value.copy(
                    categoria = it.tipoCategoria()
                )
            }, onMostrarCaixaDialogoImagem = {
                _uiState.value = _uiState.value.copy(
                    mostrarCaixaDialogoImagem = it
                )
            }, onMostrarCaixaDataInicio = {
                _uiState.value = _uiState.value.copy(
                    mostrarCaixaDataInicio = it
                )
            }, onMostrarCaixaDataFim = {
                _uiState.value = _uiState.value.copy(
                    mostrarCaixaDataFim = it
                )
            })
        }
    }

    private suspend fun carregaLivro() {
        idLivro?.let {
            val livro = dao.buscaPorId(idLivro)
            livro.collect {
                it?.let {
                    with(it) {
                        _uiState.value = _uiState.value.copy(
                            id = id,
                            nome = nome,
                            autor = autor,
                            editora = editora,
                            imagem = imagem,
                            formato = formato.tipoFormato(),
                            adquirido = adquirido,
                            leituras = leituras,
                            inicioLeitura = inicioLeitura,
                            fimLeitura = fimLeitura,
                            detalhes = detalhes,
                            categoria = categoria.tipoCategoria(),
                            tituloAppbar = R.string.titulo_editar_livro,
                        )
                    }
                }
            }
        }
    }

    fun defineTextoDatas(textoInicio: String, textoFim: String) {
        val texto1 = _uiState.value.inicioLeitura?.converteParaString() ?: textoInicio
        val texto2 = _uiState.value.fimLeitura?.converteParaString() ?: textoFim

        _uiState.update {
            it.copy(dataInicioTexto = texto1,dataFimTexto = texto2)
        }
    }

    fun carregaImagem(url: String) {
        _uiState.value = _uiState.value.copy(
            imagem = url, mostrarCaixaDialogoImagem = false
        )
    }

    suspend fun salva() {

        with(_uiState.value) {
            dao.insere(
                Livro(
                    id = id,
                    nome = nome,
                    autor = autor,
                    editora = editora,
                    imagem = imagem,
                    formato = formato.texto,
                    adquirido = adquirido,
                    leituras = leituras,
                    inicioLeitura = inicioLeitura,
                    fimLeitura = fimLeitura,
                    detalhes = detalhes,
                    categoria = categoria.texto
                )
            )
        }
    }
}