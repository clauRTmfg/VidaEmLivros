package br.com.crtalmeida.vidaemlivros.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.crtalmeida.vidaemlivros.database.dao.LivroDAO
import br.com.crtalmeida.vidaemlivros.database.model.Livro
import br.com.crtalmeida.vidaemlivros.ui.uistate.DetalhesLivroUIState
import br.com.crtalmeida.vidaemlivros.util.ID_LIVRO
import br.com.crtalmeida.vidaemlivros.util.StatusLivro
import br.com.crtalmeida.vidaemlivros.util.tipoCategoria
import br.com.crtalmeida.vidaemlivros.util.tipoFormato
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class DetalhesLivroViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val dao: LivroDAO
) : ViewModel() {

    private val idLivro = savedStateHandle.get<Long>(ID_LIVRO)

    private val _uiState = MutableStateFlow(DetalhesLivroUIState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            carregaLivro()
        }

        _uiState.update { state ->
            state.copy(onMostraDialogExclusaoMudou = {
                _uiState.value = _uiState.value.copy(
                    mostraDialogExclusao = it
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
                            categoria = categoria.tipoCategoria()
                        )
                    }
                }
                carregaStatus()
            }
        }
    }

    private fun carregaStatus() {

        val inicio: LocalDate? = _uiState.value.inicioLeitura
        val fim: LocalDate? = _uiState.value.fimLeitura
        val adquirido = _uiState.value.adquirido
        var status: StatusLivro
//        val inicioTxt = inicio?.converteParaString()
//        val fimTxt = fim?.converteParaString()

        if (inicio != null) {
            if (fim != null) {
                if (inicio <= fim) status = StatusLivro.LIDO
                else status = StatusLivro.LENDO
            } else {
                status = StatusLivro.LENDO
            }
        } else {
            if (fim == null) status = StatusLivro.NAO_LIDO
            else status = StatusLivro.LIDO
        }

        if (!adquirido) status = StatusLivro.NAO_ADQUIRIDO

//        val statustxt = when {
//            status == StatusLeitura.LIDO -> "LIDO"
//            status == StatusLeitura.NAO_LIDO -> "NAO_LIDO"
//            else -> "LENDO"
//        }
//        Log.i("DetalhesLivroViewModel", "status: $statustxt")
//        Log.i("DetalhesLivroViewModel", "inicio: $inicioTxt")
//        Log.i("DetalhesLivroViewModel", "fim: $fimTxt")

        _uiState.update {
            it.copy(status = status)
        }

    }

    private fun atualizaDatasEStatus() {

        val status = _uiState.value.status
        val dataAtual = LocalDate.now()

        if (status != StatusLivro.NAO_ADQUIRIDO) {
            if (status == StatusLivro.NAO_LIDO || status == StatusLivro.LIDO) {
                _uiState.update {
                    it.copy(inicioLeitura = dataAtual)
                }
            } else {
                atualizaLeituras()

                _uiState.update {
                    it.copy(fimLeitura = dataAtual)
                }
            }
        } else {
            _uiState.update {
                it.copy(adquirido = true)
            }
        }
    }

    private fun atualizaLeituras() {
        val leituras = _uiState.value.leituras + 1

        _uiState.update {
            it.copy(leituras = leituras)
        }
    }

    fun defineTextoBotao() {
        val status = _uiState.value.status
        _uiState.update {
            it.copy(textoBotao = status.texto)
        }
    }

    suspend fun removeLivro() {
        idLivro?.let { dao.deleta(it) }
    }

    fun onClickMostraDialogExclusao() {
        _uiState.value = _uiState.value.copy(
            mostraDialogExclusao = true
        )
    }

    suspend fun atualizaLivro() {

        atualizaDatasEStatus()

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
