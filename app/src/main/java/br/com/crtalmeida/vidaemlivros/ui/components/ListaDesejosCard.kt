package br.com.crtalmeida.vidaemlivros.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.crtalmeida.vidaemlivros.R
import br.com.crtalmeida.vidaemlivros.database.model.Livro
import br.com.crtalmeida.vidaemlivros.util.sampleLivroDesejo
import br.com.crtalmeida.vidaemlivros.ui.theme.VidaEmLivrosTheme
import coil.compose.AsyncImage

@Composable
fun ListaDesejosCard(
    livro: Livro,
    modifier: Modifier = Modifier
) {
    Card(
        modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Row {
            livro.imagem?.let { image ->
                AsyncImage(
                    model = image,
                    contentDescription = null,
                    Modifier
                        .width(80.dp)
                        .fillMaxHeight(),
                    placeholder = painterResource(id = R.drawable.placeholder),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                Modifier
                    .padding(16.dp)
                    .weight(3f)
            ) {
                Text(
                    text = livro.nome,
                    Modifier.fillMaxWidth(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500)
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = livro.autor,
                    Modifier.fillMaxWidth(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight(400),
                )
            }
        }
    }
}

@Preview
@Composable
fun ListaDesejosCardPreview() {
    VidaEmLivrosTheme {
        MeusLivrosCard(
            livro = sampleLivroDesejo
        )
    }
}
