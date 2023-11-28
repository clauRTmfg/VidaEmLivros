package br.com.crtalmeida.vidaemlivros.navigation

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import br.com.crtalmeida.vidaemlivros.database.model.Livro
import br.com.crtalmeida.vidaemlivros.ui.screens.BuscaLivrosScreen
import br.com.crtalmeida.vidaemlivros.ui.viewmodels.BuscaLivrosViewModel
import br.com.crtalmeida.vidaemlivros.util.LISTA_ORIGEM

fun NavGraphBuilder.buscaLivrosGraph(
    onPopBackStack: () -> Unit,
    onNavegaParaDetalhes: (Livro) -> Unit
) {
    composable("${Destino.BuscaLivros.rota}/{$LISTA_ORIGEM}") {
        it.arguments?.getString(LISTA_ORIGEM)?.let {
            val viewModel = hiltViewModel<BuscaLivrosViewModel>()
            val state by viewModel.uiState.collectAsState()

            BuscaLivrosScreen(
                state = state,
                origem = it,
                onVolta = onPopBackStack,
                onClickLivro = onNavegaParaDetalhes
            )
        }
    }
}

fun NavController.navigateToBuscaLivros(origem: String?, navOptions: NavOptions? = null) {
    Log.i("buscaLivrosGraph", "navigateToBuscaLivros: $origem")
    navigate("${Destino.BuscaLivros.rota}/$origem", navOptions)
}