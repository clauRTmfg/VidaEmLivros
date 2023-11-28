package br.com.crtalmeida.vidaemlivros.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.crtalmeida.vidaemlivros.database.dao.LivroDAO
import br.com.crtalmeida.vidaemlivros.database.model.Livro
import br.com.crtalmeida.vidaemlivros.navigation.Destino
import br.com.crtalmeida.vidaemlivros.ui.uistate.BuscaLivrosUIState
import br.com.crtalmeida.vidaemlivros.util.LISTA_ORIGEM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BuscaLivrosViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val dao: LivroDAO
) : ViewModel() {

    private val listaOrigem = savedStateHandle.get<String>(LISTA_ORIGEM)

    private val _uiState = MutableStateFlow(BuscaLivrosUIState())
    val uiState = _uiState.asStateFlow()

    init {

        _uiState.update { state ->
            state.copy(onValorBuscaMudou = {
                _uiState.value = _uiState.value.copy(
                    valorBusca = it
                )
                buscaLivrosPorChave(listaOrigem)
            })
        }
    }

    private fun buscaLivrosPorChave(origem: String?) {
        viewModelScope.launch {

            var livrosEncontrados: List<Livro> = emptyList()

            when(origem) {
                Destino.MeusLivros.rota -> {
                    livrosEncontrados = dao.buscaPorNomeEAutor(
                        _uiState.value.valorBusca, 1
                    ).first()
                }
                Destino.ListaDesejos.rota -> {
                    livrosEncontrados = dao.buscaPorNomeEAutor(
                        _uiState.value.valorBusca, 0
                    ).first()
                }
                else -> {}
            }

            atualizaListaContatos(livrosEncontrados)
        }
    }

    private fun atualizaListaContatos(listaEncontrada: List<Livro>) {
        _uiState.value = _uiState.value.copy(
            livros = listaEncontrada
        )
    }
}