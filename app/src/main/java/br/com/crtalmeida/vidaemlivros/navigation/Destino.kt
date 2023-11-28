package br.com.crtalmeida.vidaemlivros.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import br.com.crtalmeida.vidaemlivros.util.ID_LIVRO

sealed class Destino(val rota: String) {
    object HomeGraph : Destino("tela_principal")
    object MeusLivros : Destino("meus_livros")
    object ListaDesejos : Destino("lista_de_desejos")
    object BuscaLivros : Destino("busca_livros")

    object FormularioLivro {
        const val rota = "formulario_livro"
        val argumentos = listOf(
            navArgument(ID_LIVRO) {
                defaultValue = 0L
                type = NavType.LongType
            }
        )
    }
    object DetalhesLivro {
        const val rota = "detalhes_livro"
        val argumentos = listOf(
            navArgument(ID_LIVRO) {
                defaultValue = 0L
                type = NavType.LongType
            }
        )
    }
}

