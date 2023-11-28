package br.com.crtalmeida.vidaemlivros.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import br.com.crtalmeida.vidaemlivros.util.FORMULARIO_KEY
import br.com.crtalmeida.vidaemlivros.util.ALTERACOES_MSG
import br.com.crtalmeida.vidaemlivros.util.LIVRO_SALVO_MSG
import br.com.crtalmeida.vidaemlivros.util.REMOCAO_KEY
import br.com.crtalmeida.vidaemlivros.util.REMOCAO_MSG

@Composable
fun VidaEmLivrosNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        //startDestination = DestinosHelloApp.SplashScreen.rota,
        startDestination = Destino.HomeGraph.rota
    ) {
        homeGraph(
            onNavegaParaDetalhes = { livro ->
                navController.navegaParaDetalhes(livro.id)
            }
        )
        formularioLivroGraph(
            onPopBackStack = {idLivro ->
                retornoComMensagem(
                    navController,
                    idLivro,
                    FORMULARIO_KEY,
                    ""
                )
            }
        )
        detalhesLivroGraph(
            onVoltaComMsg = {
                retornoComMensagem(
                    navController,
                    null,
                    REMOCAO_KEY,
                    REMOCAO_MSG
                )
            },
            onEditaLivro = { idlivro ->
                navController.navegaParaFormularioLivro(idlivro)
            },
            onApenasVolta = { navController.popBackStack() }
        )
        buscaLivrosGraph(
            onPopBackStack = {
                navController.popBackStack()
            },
            onNavegaParaDetalhes = { livro ->
                navController.navegaParaDetalhes(livro.id)
            }
        )
    }

}

fun retornoComMensagem(
    navController: NavHostController,
    idLivro: Long?,
    chave: String,
    msg: String
) {
    var mensagem: String = msg
    if (chave == FORMULARIO_KEY) {
        when(idLivro) {
            0L -> mensagem = LIVRO_SALVO_MSG
            else -> mensagem = ALTERACOES_MSG
        }
    }
    navController.currentBackStackEntry
        ?.savedStateHandle
        ?.set(chave, mensagem)
    navController.popBackStack()
}



