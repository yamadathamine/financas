package com.thamineyamada.financas.model

class Resumo(private val transacoes : List<Transacao>) {

    val receita get()  = somaPor(Tipo.RECEITA)
    val despesa get()  = somaPor(Tipo.DESPESA)
    val total get() = receita - despesa

    private fun somaPor(tipo : Tipo): Double {
        return transacoes
            .filter { it.tipo == tipo }
            .sumByDouble { it.valor }
    }

}