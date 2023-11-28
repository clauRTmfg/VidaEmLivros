package br.com.crtalmeida.vidaemlivros

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LibraryAdd
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import br.com.crtalmeida.vidaemlivros.navigation.Destino
import br.com.crtalmeida.vidaemlivros.navigation.VidaEmLivrosNavHost
import br.com.crtalmeida.vidaemlivros.navigation.navegaParaFormularioLivro
import br.com.crtalmeida.vidaemlivros.navigation.navigateToBottomAppBarItemSelected
import br.com.crtalmeida.vidaemlivros.navigation.navigateToBuscaLivros
import br.com.crtalmeida.vidaemlivros.ui.components.BottomAppBarItem
import br.com.crtalmeida.vidaemlivros.ui.components.VidaEmLivrosBottomAppBar
import br.com.crtalmeida.vidaemlivros.ui.components.VidaEmLivrosTopAppBar
import br.com.crtalmeida.vidaemlivros.ui.components.bottomAppBarItems
import br.com.crtalmeida.vidaemlivros.ui.theme.VidaEmLivrosTheme
import br.com.crtalmeida.vidaemlivros.util.FORMULARIO_KEY
import br.com.crtalmeida.vidaemlivros.util.REMOCAO_KEY
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VidaEmLivrosTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    VidaEmLivrosApp()
                }
            }
        }
    }
}


@Composable
fun VidaEmLivrosApp(navController: NavHostController = rememberNavController()) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    // O CÓDIGO ABAIXO É SERVE APENAS PARA ANÁLISE DAS NAVEGAÇÕES PELO LOGCAT
    // LaunchedEffect (coroutine): este escopo não é reexecutado na recomposição .
    // Usamos o addOnDestinationChangedListener/backQueue para termos a lista
    // de rotas clicadas
//    LaunchedEffect(Unit) {
//        navController.addOnDestinationChangedListener { _, _, _ ->
//            navController.currentBackStack.value.map {
//                it.destination.route
//            }
//        }
//    }

    val backStackEntryState by navController.currentBackStackEntryAsState()
    val msgAlteracoes = backStackEntryState
        ?.savedStateHandle
        ?.getStateFlow<String?>(FORMULARIO_KEY, null)
        ?.collectAsState()
    val msgRemocao = backStackEntryState
        ?.savedStateHandle
        ?.getStateFlow<String?>(REMOCAO_KEY, null)
        ?.collectAsState()
    //backStackEntryState?.savedStateHandle?.remove<String?>("alteracoes_ok")

    val snackbarHostState = remember {
        SnackbarHostState()
    }
    msgAlteracoes?.value?.let {
        scope.launch {
            snackbarHostState.showSnackbar(message = it)
        }
    }
    msgRemocao?.value?.let {
        scope.launch {
            snackbarHostState.showSnackbar(message = it)
        }
    }

    // aqui o codigo que traz o item atual selecionado
    val currentDestination = backStackEntryState?.destination
    val currentRoute = currentDestination?.route
    val selectedItem by remember(currentDestination) {
        val item = when (currentRoute) {
            Destino.MeusLivros.rota -> BottomAppBarItem.MeusLivros
            Destino.ListaDesejos.rota -> BottomAppBarItem.Desejos
            else -> BottomAppBarItem.MeusLivros
        }
        mutableStateOf(item)
    }

    val showAppBar = when (currentRoute) {
        Destino.MeusLivros.rota, Destino.ListaDesejos.rota -> true
        else -> false
    }

    val showFab = when (currentDestination?.route) {
        Destino.MeusLivros.rota, Destino.ListaDesejos.rota -> true
        else -> false
    }

    VidaEmLivrosApp(
        snackbarHostState = snackbarHostState,
        bottomAppBarItemSelected = selectedItem,
        onBottomAppBarItemSelectedChange = { item ->
            navController.navigateToBottomAppBarItemSelected(item)
        },
        onFabClick = {
            navController.navegaParaFormularioLivro()
        },
        onClickMenu = {},
        onClickBusca = {
            val navOptions = navOptions {
                launchSingleTop = true
                popUpTo(Destino.BuscaLivros.rota)
            }
            navController.navigateToBuscaLivros(currentRoute,navOptions)
        },
        showTopBar = showAppBar,
        showBottomBar = showAppBar,
        showFab = showFab,
    ) {

        VidaEmLivrosNavHost(
            navController = navController
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VidaEmLivrosApp(
    bottomAppBarItemSelected: BottomAppBarItem = bottomAppBarItems.first(),
    onBottomAppBarItemSelectedChange: (BottomAppBarItem) -> Unit = {},
    onFabClick: () -> Unit = {},
    onClickMenu: () -> Unit = {},
    onClickBusca: () -> Unit = {},
    showTopBar: Boolean = false,
    showBottomBar: Boolean = false,
    showFab: Boolean= false,
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
    // importante : o content de um Scaffold precisa ser o último parâmetro
    content: @Composable () -> Unit
) {
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) {
                Snackbar(Modifier.padding(8.dp)) {
                    Text(
                        text = it.visuals.message,
                        Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        },
        topBar = {
            if (showTopBar) {
                VidaEmLivrosTopAppBar(
                    onClickMenu = onClickMenu,
                    onClickBusca = onClickBusca
                )
            }
        },
        bottomBar = {
            if (showBottomBar) {
                VidaEmLivrosBottomAppBar(
                    item = bottomAppBarItemSelected,
                    items = bottomAppBarItems,
                    onItemChange = onBottomAppBarItemSelectedChange,
                )
            }
        },
        floatingActionButton = {
            if (showFab) {
                FloatingActionButton(
                    onClick = onFabClick,
                    Modifier.semantics {
                        contentDescription = "adicionar novo livro"
                    }
                ) {
                    Icon(
                        Icons.Filled.LibraryAdd,
                        contentDescription = null
                    )
                }
            }
        }
    ) {
        Box(
            modifier = Modifier.padding(it)
        ) {
            content()
        }
    }
}

@Preview
@Composable
private fun VidaEmLivrosAppPreview() {
    VidaEmLivrosTheme {
        Surface {
            VidaEmLivrosApp(content = {})
        }
    }
}

