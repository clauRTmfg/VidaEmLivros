package br.com.crtalmeida.vidaemlivros.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.crtalmeida.vidaemlivros.R
import br.com.crtalmeida.vidaemlivros.ui.components.FormularioOpcoes
import br.com.crtalmeida.vidaemlivros.ui.components.caixaDialogoData
import br.com.crtalmeida.vidaemlivros.ui.theme.VidaEmLivrosTheme
import br.com.crtalmeida.vidaemlivros.ui.uistate.FormularioLivroUIState
import br.com.crtalmeida.vidaemlivros.util.opcoesCategorias
import br.com.crtalmeida.vidaemlivros.util.opcoesFormatos


@Composable
fun FormularioLivroScreen(
    state: FormularioLivroUIState,
    modifier: Modifier = Modifier,
    onClickSalva: () -> Unit = {},
    onCarregaImagem: (String) -> Unit = {}
) {
    Scaffold(
        topBar = {
            state.tituloAppbar?.let { titulo ->
                FormularioLivroAppBar(stringResource(id = titulo))
            }
        },
    ) { paddingValues ->
        Column(
            modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    //.padding(vertical = 8.dp)
//                    .weight(1f)
//            ) {
//                AsyncImage(
//                    modifier = Modifier
//                        .size(30.dp)
//                        .clip(CircleShape)
//                        .clickable {
//                            state.onMostrarCaixaDialogoImagem(true)
//                        },
//                    model = ImageRequest.Builder(LocalContext.current)
//                        .data(state.imagem).build(),
//                    placeholder = painterResource(R.drawable.default_profile_picture),
//                    error = painterResource(R.drawable.default_profile_picture),
//                    contentScale = ContentScale.Crop,
//                    contentDescription = stringResource(id = R.string.foto_perfil_contato),
//                )
//                Text(
//                    text = stringResource(R.string.adicionar_imagem),
//                    style = MaterialTheme.typography.subtitle1
//                )
//            }
            Column(
                Modifier
                    .padding(horizontal = 10.dp)
                    .weight(5f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val focoAtual = LocalFocusManager.current
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.LibraryBooks,
                            contentDescription = null
                        )
                    },
                    value = state.nome,
                    onValueChange = state.onNomeMudou,
                    label = { Text(stringResource(id = R.string.nome)) },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = (KeyboardActions(onNext = { focoAtual.moveFocus(FocusDirection.Next) }))
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null
                        )
                    },
                    value = state.autor,
                    onValueChange = state.onAutorMudou,
                    label = { Text(stringResource(id = R.string.autor)) },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = (KeyboardActions(onNext = { focoAtual.moveFocus(FocusDirection.Next) }))
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.editora,
                    onValueChange = state.onEditoraMudou,
                    label = { Text(stringResource(id = R.string.editora)) },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = (KeyboardActions(onNext = { focoAtual.moveFocus(FocusDirection.Next) }))
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.detalhes,
                    onValueChange = state.onDetalhesMudou,
                    label = { Text(stringResource(id = R.string.detalhes)) },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = (KeyboardActions(onNext = { focoAtual.moveFocus(FocusDirection.Next) }))
                )


                Row(
                    modifier.fillMaxWidth()
                ) {

                    FormularioOpcoes(
                        modifier = modifier
                            .fillMaxWidth()
                            .weight(1f),
                        titulo = stringResource(id = R.string.categoria),
                        valor = state.categoria.texto,
                        opcoes = opcoesCategorias,
                        onOpcaoEscolhida = state.onCategoriaMudou,
                    )

                    FormularioOpcoes(
                        modifier = modifier
                            .fillMaxWidth()
                            .weight(1f),
                        titulo = stringResource(id = R.string.formato),
                        valor = state.formato.texto,
                        opcoes = opcoesFormatos,
                        onOpcaoEscolhida = state.onFormatoMudou
                    )
                }

                Row(
                    modifier.fillMaxWidth()
                ) {
                    OutlinedButton(
                        onClick = { state.onMostrarCaixaDataInicio(true) },
                        modifier.weight(1f),
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null,
                            Modifier.padding(8.dp)
                        )
                        Text(text = state.dataInicioTexto)
                    }
                    OutlinedButton(
                        onClick = { state.onMostrarCaixaDataFim(true) },
                        modifier.weight(1f),
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null,
                            Modifier.padding(8.dp)
                        )
                        Text(text = state.dataFimTexto)
                    }
                }

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.leituras.toString(),
                    onValueChange = state.onLeiturasMudou,
                    label = { Text(stringResource(id = R.string.leituras)) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = (KeyboardActions(onNext = { focoAtual.moveFocus(FocusDirection.Next) }))
                )

                //val (checkedState, onStateChange) = remember { mutableStateOf(false) }
                var checkedState by remember { mutableStateOf(false) }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp),
//                        .toggleable(
//                            value = checkedState,
//                            onValueChange = {
//                                onStateChange(!checkedState)
//                                state.onAdquiridoMudou
//                            },
//                            role = Role.Checkbox
//                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = checkedState,
                        onCheckedChange = {
                            checkedState = it
                            state.onAdquiridoMudou(checkedState)
                        }
                    )
                    Text(
                        text = "Adicionar à lista de desejos",
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }


                Spacer(Modifier.height(8.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(56.dp),
                    onClick = onClickSalva
                ) {
                    Text(text = stringResource(R.string.salvar))
                }
            }

            if (state.mostrarCaixaDataInicio) {
                caixaDialogoData(
                    LocalContext.current,
                    dataAtual = state.inicioLeitura,
                    onClickDispensar = { state.onMostrarCaixaDataInicio(false) },
                    onClickDataSelecionada = state.onInicioLeituraMudou
                )
            }

            if (state.mostrarCaixaDataFim) {
                caixaDialogoData(
                    LocalContext.current,
                    dataAtual = state.fimLeitura,
                    onClickDispensar = { state.onMostrarCaixaDataFim(false) },
                    onClickDataSelecionada = state.onFimLeituraMudou
                )
            }

//            if (state.mostrarCaixaDialogoImagem) {
//                CaixaDialogoImagem(
//                    state.fotoPerfil,
//                    onFotoPerfilMudou = state.onFotoPerfilMudou,
//                    onClickDispensar = { state.onMostrarCaixaDialogoImagem(false) },
//                    onClickSalvar = { onCarregaImagem(it) }
//                )
//            }

        }
    }
}

@Composable
fun FormularioLivroAppBar(tituloAppBar: String) {
    TopAppBar(
        title = { Text(text = tituloAppBar) },
    )
}

@Preview
@Composable
fun FormularioLivroScreenPreview() {
    VidaEmLivrosTheme {
        FormularioLivroScreen(
            state = FormularioLivroUIState()
        )
    }
}