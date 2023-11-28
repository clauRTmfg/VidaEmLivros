package br.com.crtalmeida.vidaemlivros.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.crtalmeida.vidaemlivros.R
import br.com.crtalmeida.vidaemlivros.ui.components.CaixaDialogoConfirmacao
import br.com.crtalmeida.vidaemlivros.ui.uistate.DetalhesLivroUIState
import br.com.crtalmeida.vidaemlivros.util.StatusLivro
import br.com.crtalmeida.vidaemlivros.util.converteParaString
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun DetalhesLivroScreen(
    state: DetalhesLivroUIState,
    modifier: Modifier = Modifier,
    onVoltaTela: () -> Unit = {},
    onEditaLivro: () -> Unit = {},
    onExcluiLivro: () -> Unit = {},
    onClickMostraDialogExclusao: () -> Unit = {},
    onAtualizaStatus: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            DetalhesLivroAppBar(
                onClickVoltar = onVoltaTela,
                onClickExcluir = onClickMostraDialogExclusao,
                onClickEditar = onEditaLivro
            )
        },
    ) { paddingValues ->
        Column(
            modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                contentScale = ContentScale.Crop,
                model = ImageRequest.Builder(LocalContext.current).data(state.imagem).build(),
                placeholder = painterResource(R.drawable.default_profile_picture),
                error = painterResource(R.drawable.default_profile_picture),
                contentDescription = stringResource(R.string.imagem_capa_livro),
            )
            Text(
                modifier = Modifier.padding(vertical = 16.dp),
                text = state.nome,
                style = MaterialTheme.typography.h5
            )

            Divider(thickness = 1.dp)

            Column(
                Modifier.padding(16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {

                Text(
                    modifier = Modifier.padding(bottom = 22.dp),
                    text = stringResource(R.string.informacoes),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.subtitle1
                )

                Text(
                    text = "Autor: ${state.autor}",
                    style = MaterialTheme.typography.h6
                )

                Text(
                    text = "Editora: ${state.editora}",
                    style = MaterialTheme.typography.h6
                )

                Text(
                    text = "Formato: ${state.formato.texto}",
                    style = MaterialTheme.typography.h6
                )

                state.inicioLeitura?.let {
                    val inicio = it.converteParaString()
                    Text(
                        text = "Inicio leitura: $inicio",
                        style = MaterialTheme.typography.h6
                    )
                }

                state.fimLeitura?.let {
                    val fim = it.converteParaString()
                    Text(
                        text = "Fim leitura: $fim",
                        style = MaterialTheme.typography.h6
                    )
                }

                state.leituras.let {
                    Text(
                        text = "Leituras: $it",
                        style = MaterialTheme.typography.h6
                    )
                }

                Text(
                    text = "Detalhes: ${state.detalhes}",
                    style = MaterialTheme.typography.h6
                )

                Text(
                    text = "Categoria: ${state.categoria.texto}",
                    style = MaterialTheme.typography.h6
                )

//                val status = when {
//                    state.status == StatusLivro.LIDO -> "LIDO"
//                    state.status == StatusLivro.NAO_LIDO -> "NAO_LIDO"
//                    state.status == StatusLivro.LENDO -> "LENDO"
//                    else -> "NAO_ADQUIRIDO"
//                }
//
//                Text(
//                    text = "Status: $status",
//                    style = MaterialTheme.typography.h6
//                )

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(56.dp),
                    onClick = onAtualizaStatus
                ) {
                    Text(text = state.textoBotao)
                }

            }

            if (state.mostraDialogExclusao) {
                CaixaDialogoConfirmacao(
                    titulo = stringResource(R.string.tem_certeza),
                    onClikConfirma = onExcluiLivro,
                    onClickCancela = { state.onMostraDialogExclusaoMudou(false) },
                )
            }

        }
    }
}


@Composable
fun DetalhesLivroAppBar(
    onClickVoltar: () -> Unit,
    onClickExcluir: () -> Unit,
    onClickEditar: () -> Unit
) {
    TopAppBar {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = onClickVoltar
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    tint = Color.White,
                    contentDescription = stringResource(R.string.voltar)
                )
            }

            Row {
                IconButton(
                    onClick = onClickEditar
                ) {
                    Icon(
                        Icons.Default.Edit,
                        tint = Color.White,
                        contentDescription = stringResource(R.string.editar)
                    )
                }

                IconButton(onClick = { onClickExcluir() }) {
                    Icon(
                        Icons.Default.Delete,
                        tint = Color.White,
                        contentDescription = stringResource(R.string.excluir)
                    )
                }
            }
        }
    }
}
