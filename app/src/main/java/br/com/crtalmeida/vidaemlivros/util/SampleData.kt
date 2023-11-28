package br.com.crtalmeida.vidaemlivros.util

import br.com.crtalmeida.vidaemlivros.database.model.Livro
import java.time.LocalDate

val sampleLivroComImagem = Livro(
    nome = "O Evangelho Segundo o Espiritismo",
    autor = "Allan Kardec",
    editora = "Edicel",
    imagem = "https://picsum.photos/1920/1080",
    formato = Formato.CAPA_COMUM.texto,
    adquirido = true,
    leituras = 2,
    inicioLeitura = LocalDate.parse("2021-11-15"),
    fimLeitura = LocalDate.parse("2022-04-12"),
    detalhes = "Evangelho sempre !",
    categoria = Categoria.ESPIRITA.texto
)

val sampleLivroSemImagem = Livro(
    nome = "Cavalo de Troia 5",
    autor = "J. J. Benítez",
    editora = "Planeta",
    formato = Formato.EBOOK.texto,
    adquirido = true,
    leituras = 0,
    inicioLeitura = LocalDate.parse("2022-08-01"),
    fimLeitura = LocalDate.parse("2023-06-12"),
    detalhes = "Continuando estória de Jasão",
    categoria = Categoria.FICCAO.texto
)

val sampleLivroDesejo = Livro(
    nome = "Boa Nova",
    autor = "Chico Xavier",
    editora = "FEB",
    formato = "Capa comum",
    adquirido = false
)

val sampleLivros: List<Livro> = listOf(sampleLivroComImagem,
    sampleLivroSemImagem,
    sampleLivroComImagem,
    sampleLivroComImagem,
    sampleLivroComImagem,
    sampleLivroComImagem,
    sampleLivroComImagem,
    sampleLivroComImagem,
    sampleLivroComImagem, )
val sampleDesejos: List<Livro> = listOf(sampleLivroDesejo)


