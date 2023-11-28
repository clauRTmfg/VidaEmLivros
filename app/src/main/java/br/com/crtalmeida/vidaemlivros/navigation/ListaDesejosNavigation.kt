package br.com.crtalmeida.vidaemlivros.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import br.com.crtalmeida.vidaemlivros.database.model.Livro
import br.com.crtalmeida.vidaemlivros.ui.screens.ListaDesejosScreen
import br.com.crtalmeida.vidaemlivros.ui.viewmodels.ListaDesejosViewModel

fun NavGraphBuilder.listaDesejosGraph(
    onNavegaParaDetalhes: (Livro) -> Unit
) {
    composable(route = Destino.ListaDesejos.rota) {
        val viewModel = hiltViewModel<ListaDesejosViewModel>()
        val state by viewModel.uiState.collectAsState()

        ListaDesejosScreen(
            state = state,
            onLivroClick = onNavegaParaDetalhes
        )
    }
}

fun NavController.navigateToListaDesejos(navOptions: NavOptions? = null) {
    navigate(Destino.ListaDesejos.rota, navOptions)
}