package br.com.crtalmeida.vidaemlivros.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.crtalmeida.vidaemlivros.ui.screens.DetalhesLivroScreen
import br.com.crtalmeida.vidaemlivros.ui.viewmodels.DetalhesLivroViewModel
import br.com.crtalmeida.vidaemlivros.util.ID_LIVRO
import kotlinx.coroutines.launch

fun NavGraphBuilder.detalhesLivroGraph(
    onVoltaComMsg: () -> Unit,
    onEditaLivro: (Long) -> Unit,
    onApenasVolta: () -> Unit
) {
    composable(
        route = "${Destino.DetalhesLivro.rota}/{$ID_LIVRO}",
        arguments = Destino.DetalhesLivro.argumentos
    ) { navBackStackEntry ->
        navBackStackEntry.arguments?.getLong(ID_LIVRO)?.let { idLivro ->
            val viewModel = hiltViewModel<DetalhesLivroViewModel>()
            val state by viewModel.uiState.collectAsState()
            val scope = rememberCoroutineScope()

            LaunchedEffect(state.status) {
                viewModel.defineTextoBotao()
            }

            val coroutineScope = rememberCoroutineScope()

            DetalhesLivroScreen(
                state = state,
                onVoltaTela = onApenasVolta,
                onExcluiLivro = {
                    scope.launch {
                        viewModel.removeLivro()
                    }
                    onVoltaComMsg()
                },
                onClickMostraDialogExclusao = {
                    viewModel.onClickMostraDialogExclusao()
                },
                onEditaLivro = {
                    onEditaLivro(idLivro)
                },
                onAtualizaStatus = {
                    coroutineScope.launch {
                        viewModel.atualizaLivro()
                    }
                }
            )
        }
    }
}

fun NavController.navegaParaDetalhes(id: Long) {
    navigate("${Destino.DetalhesLivro.rota}/$id")
}