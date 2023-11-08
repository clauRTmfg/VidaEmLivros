package br.com.crtalmeida.vidaemlivros.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

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
            //onNavegaParaBuscaLivros = { navController.navegaParaBuscaLivros() }
        )
//        detalhesLivro(
//            onNavigateToCheckout = {
//                navController.navegaParaEditar()
//            },
//            onPopBackStack = {
//                navController.navigateUp()
//            },
//        )
//        novoLivro(
//            onPopBackStack = {
//                navController.currentBackStackEntry
//                    ?.savedStateHandle
//                    ?.set("order_done", "Pedido realizado com sucesso 👍😊")
//                navController.navigateUp()
//            },
//        )
    }
}

fun NavHostController.navegaParaDetalhes(idLivro: Long) {
    //navegaDireto("${DetalhesContato.rota}/$idContato")
}

fun NavHostController.navegaParaNovoLivro(idContato: Long = 0L) {
    //navigate("${FormularioContato.rota}/$idContato")
}

fun NavHostController.navegaParaDialogUsuarios(idUsuario: String) {
    //navigate("${ListaUsuarios.rota}/$idUsuario")
}

fun NavHostController.navegaParaBuscaLivros() {
    //navigate(DestinosHelloApp.BuscaContatos.rota)
}

fun NavHostController.navegaParaDesejos() {

}

fun NavHostController.navegaParaLidos() {

}