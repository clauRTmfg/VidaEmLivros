package br.com.crtalmeida.vidaemlivros.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material.icons.filled.LocalLibrary
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import br.com.crtalmeida.vidaemlivros.ui.theme.VidaEmLivrosTheme

// o modificador sealed impede que outros objetos BottomAppBarItem
// sejam criados fora desta classe
sealed class BottomAppBarItem(
    val label: String,
    val icon: ImageVector
) {
    object MeusLivros: BottomAppBarItem(
        label = "Biblioteca",
        icon = Icons.Filled.LibraryBooks
    )

    object Desejos: BottomAppBarItem(
        label = "Desejos",
        icon = Icons.Filled.Favorite
    )

}

val bottomAppBarItems = listOf(
    BottomAppBarItem.MeusLivros,
    BottomAppBarItem.Desejos,
)

@Composable
fun VidaEmLivrosBottomAppBar(
    item: BottomAppBarItem,
    modifier: Modifier = Modifier,
    items: List<BottomAppBarItem> = emptyList(),
    onItemChange: (BottomAppBarItem) -> Unit = {}
) {
    NavigationBar(modifier) {
        items.forEach {
            val label = it.label
            val icon = it.icon
            NavigationBarItem(
                icon = { Icon(icon, contentDescription = label) },
                label = { Text(label) },
                selected = item.label == label,
                onClick = {
                    onItemChange(it)
                }
            )
        }
    }
}

@Preview
@Composable
fun VidaEmLivrosBottomAppBar() {
    VidaEmLivrosTheme {
        VidaEmLivrosBottomAppBar(
            item = bottomAppBarItems.first(),
            items = bottomAppBarItems
        )
    }
}