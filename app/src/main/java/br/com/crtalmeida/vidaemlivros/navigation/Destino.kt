package br.com.crtalmeida.vidaemlivros.navigation

sealed class Destino(val rota: String) {
    object HomeGraph : Destino("tela_principal")
    object MeusLivros : Destino("meus_livros")
    object ListaDesejos : Destino("lista_de_desejos")

}