package br.com.crtalmeida.vidaemlivros.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.crtalmeida.vidaemlivros.R
import br.com.crtalmeida.vidaemlivros.ui.screens.FormularioLivroScreen
import br.com.crtalmeida.vidaemlivros.ui.viewmodels.FormularioLivroViewModel
import br.com.crtalmeida.vidaemlivros.util.ID_LIVRO
import kotlinx.coroutines.launch

fun NavGraphBuilder.formularioLivroGraph(
    onPopBackStack: (Long) -> Unit
) {
    composable(
        route = "${Destino.FormularioLivro.rota}/{$ID_LIVRO}",
        arguments = Destino.FormularioLivro.argumentos
    ) { navBackStackEntry ->
        navBackStackEntry.arguments?.getLong(ID_LIVRO)?.let {
            val viewModel = hiltViewModel<FormularioLivroViewModel>()
            val state by viewModel.uiState.collectAsState()
            val context = LocalContext.current

            LaunchedEffect(state.inicioLeitura, state.fimLeitura) {
                viewModel.defineTextoDatas(
                    context.getString(R.string.inicio_leitura),
                    context.getString(R.string.fim_leitura)
                )
            }

            val coroutineScope = rememberCoroutineScope()

            FormularioLivroScreen(
                state = state,
                onClickSalva = {
                    coroutineScope.launch {
                        viewModel.salva()
                    }
                    onPopBackStack(state.id)
                },
                onCarregaImagem = {
                    viewModel.carregaImagem(it)
                }
            )
        }
    }
}

fun NavController.navegaParaFormularioLivro(id: Long = 0L) {
    navigate("${Destino.FormularioLivro.rota}/$id")
}