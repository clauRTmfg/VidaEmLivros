package br.com.crtalmeida.vidaemlivros.ui.viewmodels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.sqlite.db.SimpleSQLiteQuery
import br.com.crtalmeida.vidaemlivros.database.dao.LivroDAO
import br.com.crtalmeida.vidaemlivros.ui.uistate.MeusLivrosUIState
import br.com.crtalmeida.vidaemlivros.util.ID_QUERY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MeusLivrosViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val dao: LivroDAO
) : ViewModel() {

    private val _uiState = MutableStateFlow(MeusLivrosUIState())
    val uiState = _uiState.asStateFlow()

    init {

        viewModelScope.launch {

//            val queryString: String = "SELECT * FROM Livro WHERE adquirido = 1 AND leituras > 0"
//            val query = SimpleSQLiteQuery(queryString)
//            val livros = dao.buscaLivros(query)

            val livros = dao.buscaMeusLivros()
            livros?.collect {
                _uiState.value = _uiState.value.copy(
                    livros = it
                )
            }
        }
    }
}