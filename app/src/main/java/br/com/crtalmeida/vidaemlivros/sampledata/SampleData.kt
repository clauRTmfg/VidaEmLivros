package br.com.crtalmeida.vidaemlivros.sampledata

import br.com.crtalmeida.vidaemlivros.database.model.Livro

val sampleLivroComImagem = Livro(
    nome = "O Evangelho Segundo o Espiritismo",
    autor = "Allan Kardec",
    editora = "Edicel",
    imagem = "https://picsum.photos/1920/1080",
    formato = "Capa comum",
    adquirido = true,
    lido = true,
    inicioLeitura = "20230201",
    fimLeitura = "20230520",
    edicao = "nova",
    observacao = "Evangelho sempre !",
    categoria = "Espírita"
)

val sampleLivroSemImagem = Livro(
    nome = "Cavalo de Troia 5",
    autor = "J. J. Benítez",
    editora = "Planeta",
    formato = "eBook",
    adquirido = true,
    lido = false,
    inicioLeitura = "20221004",
    edicao = "velha",
    observacao = "Continuando estória de Jasão",
    categoria = "Ficção"
)

val sampleLivroDesejo = Livro(
    nome = "Boa Nova",
    autor = "Chico Xavier",
    formato = "Capa comum",
    adquirido = false,
    lido = false
)

val sampleLivros: List<Livro> = listOf(sampleLivroComImagem,sampleLivroSemImagem)
val sampleDesejos: List<Livro> = listOf(sampleLivroDesejo)


