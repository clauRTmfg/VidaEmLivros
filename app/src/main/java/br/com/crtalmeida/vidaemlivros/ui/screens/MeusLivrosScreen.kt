package br.com.crtalmeida.vidaemlivros.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.crtalmeida.vidaemlivros.database.model.Livro
import br.com.crtalmeida.vidaemlivros.sampledata.sampleLivros
import br.com.crtalmeida.vidaemlivros.ui.components.LivroCard
import br.com.crtalmeida.vidaemlivros.ui.theme.VidaEmLivrosTheme
import br.com.crtalmeida.vidaemlivros.ui.theme.caveatFont
import br.com.crtalmeida.vidaemlivros.ui.uistate.MeusLivrosUIState

@Composable
fun MeusLivrosScreen(
    modifier: Modifier = Modifier,
    title: String = "Minha biblioteca",
    onLivroClick: (Livro) -> Unit = {},
    state: MeusLivrosUIState = MeusLivrosUIState()
) {
    //val livros = state.livros
    val livros = sampleLivros

    Column(
        modifier
            .fillMaxSize()
    ) {
        Surface {
            Text(
                text = title,
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                fontFamily = caveatFont,
                fontSize = 32.sp,
                textAlign = TextAlign.Center
            )
        }
        LazyColumn(
            modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(livros) {livro ->
                LivroCard(
                    livro = livro,
                    Modifier.semantics {
                        contentDescription = "livro da minha biblioteca"
                    }.clickable {
                        onLivroClick(livro)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun MeusLivrosScreenPreview() {
    VidaEmLivrosTheme {
        Surface {
            MeusLivrosScreen(
                title = "Minha biblioteca",
                state = MeusLivrosUIState(livros = sampleLivros)
            )
        }
    }
}