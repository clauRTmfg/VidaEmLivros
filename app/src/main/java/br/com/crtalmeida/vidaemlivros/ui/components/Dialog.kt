package br.com.crtalmeida.vidaemlivros.ui.components

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import br.com.crtalmeida.vidaemlivros.R
import br.com.crtalmeida.vidaemlivros.util.converteParaString
import br.com.crtalmeida.vidaemlivros.util.dMyyyy
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

fun caixaDialogoData(
    context: Context,
    dataAtual: LocalDate?,
    onClickDispensar: () -> Unit = {},
    onClickDataSelecionada: (dataSelecionada: LocalDate) -> Unit = {}
) {
    val formatadorDeData = DateTimeFormatter.ofPattern(dMyyyy)
    val dataLocal = if (dataAtual == null) LocalDate.now()
    else LocalDate.parse(dataAtual.converteParaString(), formatadorDeData)

    val anoAtual = dataLocal.year
    val mesAtual = dataLocal.monthValue
    val diaAtual = dataLocal.dayOfMonth

    val datePickerDialog = DatePickerDialog(
        context, { _: DatePicker, ano, mes, dia ->
            val dataSelecionada = LocalDate.parse("$dia/${mes + 1}/$ano", formatadorDeData)
            onClickDataSelecionada(dataSelecionada)
            //onClickDataSelecionada(dataSelecionada.format(formatadorDeData))
        }, anoAtual, (mesAtual - 1), diaAtual
    )

    datePickerDialog.setOnDismissListener {
        onClickDispensar()
    }
    datePickerDialog.show()
}

//@Composable
//fun CaixaDialogoImagem(
//    fotoPerfil: String,
//    modifier: Modifier = Modifier,
//    onFotoPerfilMudou: (String) -> Unit = {},
//    onClickDispensar: () -> Unit = {},
//    onClickSalvar: (urlImagem: String) -> Unit = {}
//) {
//    Dialog(
//        onDismissRequest = onClickDispensar,
//        content = {
//            Column(
//                modifier
//                    .clip(RoundedCornerShape(5))
//                    .heightIn(250.dp, 400.dp)
//                    .widthIn(200.dp)
//                    .background(Color.White)
//                    .padding(16.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                AsyncImagePerfil(
//                    urlImagem = fotoPerfil,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(200.dp)
//                        .clip(RoundedCornerShape(5, 5))
//                )
//
//                OutlinedTextField(modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 16.dp)
//                    .heightIn(max = 80.dp),
//                    value = fotoPerfil,
//                    onValueChange = onFotoPerfilMudou,
//                    label = { Text(stringResource(id = R.string.link_imagem)) })
//
//                Spacer(Modifier.height(16.dp))
//
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.End
//                ) {
//                    TextButton(onClick = onClickDispensar) {
//                        Text(text = stringResource(R.string.cancelar))
//                    }
//                    TextButton(onClick = { onClickSalvar(fotoPerfil) }) {
//                        Text(text = stringResource(R.string.salvar))
//                    }
//                }
//            }
//        }
//    )
//}

@Composable
fun CaixaDialogoConfirmacao(
    titulo: String,
    onClikConfirma: () -> Unit = {},
    onClickCancela: () -> Unit = {}
) {
    AlertDialog(
        title = {
            Text(text = titulo)
        },
        onDismissRequest = onClickCancela,
        confirmButton = {
            TextButton(onClikConfirma) {
                Text(text = stringResource(id = R.string.confirmar))
            }
        },
        dismissButton = {
            TextButton(onClickCancela) {
                Text(text = stringResource(id = R.string.cancelar))
            }
        }
    )
}

//@Preview
//@Composable
//fun CaixaDialogoImagemPreview() {
//    HelloAppTheme {
//        CaixaDialogoImagem("")
//    }
//}

//@Preview
//@Composable
//fun CaixaDialogoConfirmacaoPreview() {
//    HelloAppTheme {
//        CaixaDialogoConfirmacao(
//            titulo = stringResource(R.string.tem_certeza),
//            mensagem = stringResource(R.string.aviso_apagar_usuario),
//        )
//    }
//}