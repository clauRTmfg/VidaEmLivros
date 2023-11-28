package br.com.crtalmeida.vidaemlivros.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FormularioOpcoes(
    modifier: Modifier = Modifier,
    titulo: String,
    valor: String,
    opcoes: List<String>,
    onOpcaoEscolhida: (String) -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(opcoes[0]) }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = Modifier.fillMaxWidth()
    ) {
        opcoes.forEach { option ->
            DropdownMenuItem(
                onClick = {
                    selectedOption = option
                    expanded = false
                    onOpcaoEscolhida(option)
                }
            ) {
                Text(text = option)
            }
        }
    }

    OutlinedTextField(
        value = valor,
        onValueChange = { },
        label = { Text(titulo) },
        modifier = modifier,
        readOnly = true,
        trailingIcon = {
            IconButton(
                onClick = {
                    expanded = !expanded
                }
            ) {
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
            }
        }
    )
}

