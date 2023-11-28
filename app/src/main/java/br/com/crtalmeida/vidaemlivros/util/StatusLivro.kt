package br.com.crtalmeida.vidaemlivros.util

sealed class StatusLivro(val texto: String) {

    object LIDO : StatusLivro("Ler de novo")
    object NAO_LIDO : StatusLivro("Bora ler")
    object LENDO : StatusLivro("Terminei")
    object NAO_ADQUIRIDO : StatusLivro("Já adquiri")
}
