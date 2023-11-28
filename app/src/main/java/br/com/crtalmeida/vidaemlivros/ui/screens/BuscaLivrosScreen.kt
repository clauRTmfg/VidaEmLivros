package br.com.crtalmeida.vidaemlivros.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.crtalmeida.vidaemlivros.R
import br.com.crtalmeida.vidaemlivros.database.model.Livro
import br.com.crtalmeida.vidaemlivros.navigation.Destino
import br.com.crtalmeida.vidaemlivros.ui.components.ListaDesejosCard
import br.com.crtalmeida.vidaemlivros.ui.components.MeusLivrosCard
import br.com.crtalmeida.vidaemlivros.ui.theme.VidaEmLivrosTheme
import br.com.crtalmeida.vidaemlivros.ui.uistate.BuscaLivrosUIState

@Composable
fun BuscaLivrosScreen(
    state: BuscaLivrosUIState,
    origem: String,
    modifier: Modifier = Modifier,
    onClickLivro: (Livro) -> Unit = {},
    onVolta: () -> Unit = {}
) {
    val livros = state.livros

    Scaffold(
        modifier = modifier,
        topBar = {
            AppBarBuscaLivros(
                onValorMudou = {
                    state.onValorBuscaMudou(it)
                },
                onClickVolta = onVolta,
                valor = state.valorBusca
            )
        },
    ) { paddingValues ->

        LazyColumn(
            modifier
                .padding(paddingValues)
                .fillMaxSize(),
            //verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(livros) {livro ->
                when(origem) {
                    Destino.MeusLivros.rota -> {
                        MeusLivrosCard(
                            livro = livro,
                            Modifier
                                .clickable {
                                    onClickLivro(livro)
                                }
                        )
                    }
                    Destino.ListaDesejos.rota -> {
                        ListaDesejosCard(
                            livro = livro,
                            Modifier
                                .clickable {
                                    onClickLivro(livro)
                                }
                        )
                    }
                    else -> {}
                }
            }
        }
    }
}

@Composable
fun AppBarBuscaLivros(
    valor: String,
    onValorMudou: (String) -> Unit = {},
    onClickVolta: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onClickVolta
            ) {
                Icon(
                    Icons.Default.ArrowBack, contentDescription = stringResource(R.string.voltar)
                )
            }

            BasicTextField(
                value = valor,
                onValueChange = onValorMudou,
                decorationBox = { valorInterno ->
                    Box(
                        Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        if (valor.isEmpty()) {
                            Text(stringResource(R.string.pesquisar_contatos), color = Color.Gray)
                        }
                        valorInterno()
                    }
                },
                cursorBrush = SolidColor(MaterialTheme.colors.primary),
            )
        }
        Divider(thickness = 1.dp)
    }
}


@Preview
@Composable
fun AppBarBuscaContatosPreview() {
    VidaEmLivrosTheme {
        AppBarBuscaLivros("")
    }
}

//@Preview
//@Composable
//fun BuscaContatosPreview() {
//    VidaEmLivrosTheme {
//        BuscaLivrosScreen(
//            state = BuscaLivrosUIState(
//                contatos = contatosExemplo
//            )
//        )
//    }
//}