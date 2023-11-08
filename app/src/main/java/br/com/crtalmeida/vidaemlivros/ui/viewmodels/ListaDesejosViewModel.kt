package br.com.crtalmeida.vidaemlivros.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.crtalmeida.vidaemlivros.database.dao.LivroDAO
import br.com.crtalmeida.vidaemlivros.ui.uistate.ListaDesejosUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListaDesejosViewModel @Inject constructor(
    private val dao: LivroDAO
) : ViewModel() {

    private val _uiState = MutableStateFlow(ListaDesejosUIState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val livros = dao.buscaListaDesejos()
            //atualizando o state com os livros buscados no banco de dados
            livros?.collect {
                _uiState.value = _uiState.value.copy(
                    livros = it
                )
            }
        }
    }
}