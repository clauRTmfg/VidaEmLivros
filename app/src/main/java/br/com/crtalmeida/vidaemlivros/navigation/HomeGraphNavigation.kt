package br.com.crtalmeida.vidaemlivros.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navOptions
import androidx.navigation.navigation
import br.com.crtalmeida.vidaemlivros.database.model.Livro
import br.com.crtalmeida.vidaemlivros.ui.components.BottomAppBarItem

fun NavGraphBuilder.homeGraph(
    onNavegaParaDetalhes: (Livro) -> Unit
) {
    navigation(
        startDestination = Destino.MeusLivros.rota,
        route = Destino.HomeGraph.rota
    ) {
        meusLivrosGraph(
            onNavegaParaDetalhes
        )
        listaDesejosGraph(
            onNavegaParaDetalhes
        )
    }
}

//fun NavController.navigateToHomeGraph() {
//    navigate(Destino.HomeGraph.rota)
//}

fun NavController.navigateToBottomAppBarItemSelected(
    item: BottomAppBarItem
) {
    val (route, navigateToItem) = when (item) {
        BottomAppBarItem.MeusLivros -> Pair(
            Destino.MeusLivros.rota,
            ::navigateToMeusLivros
        )

        BottomAppBarItem.Desejos -> Pair(
            Destino.ListaDesejos.rota,
            ::navigateToListaDesejos
        )

    }

    // aqui usamos estas duas opções pra garantir que na backstack
    // haja apenas uma instancia de cada destino clicado.
    // launchSingleTop é para que uma tela não seja recarregada caso
    // seja clicada várias vezes.
    // popUpTo remove da backstack todos itens acima da rota em questão
    val navOptions = navOptions {
        launchSingleTop = true
        popUpTo(route)
    }

    navigateToItem(navOptions)
}