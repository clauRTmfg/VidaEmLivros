package br.com.crtalmeida.vidaemlivros.navigation

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import br.com.crtalmeida.vidaemlivros.R
import br.com.crtalmeida.vidaemlivros.database.model.Livro
import br.com.crtalmeida.vidaemlivros.ui.screens.MeusLivrosScreen
import br.com.crtalmeida.vidaemlivros.ui.viewmodels.MeusLivrosViewModel

fun NavGraphBuilder.meusLivrosGraph(
    onNavegaParaDetalhes: (Livro) -> Unit
) {
    composable(route = Destino.MeusLivros.rota) {
        val viewModel = hiltViewModel<MeusLivrosViewModel>()
        val state by viewModel.uiState.collectAsState()

        MeusLivrosScreen(
            state = state,
            onLivroClick = onNavegaParaDetalhes
        )
    }
}

fun NavController.navigateToMeusLivros(navOptions: NavOptions? = null) {
    navigate(Destino.MeusLivros.rota, navOptions)
}