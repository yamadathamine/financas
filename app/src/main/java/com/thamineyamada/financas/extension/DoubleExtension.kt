package com.thamineyamada.financas.extension

import java.text.DecimalFormat
import java.util.Locale

fun Double.formataParaBrasileiro() : String {
    val currency = DecimalFormat.getCurrencyInstance(Locale("pt", "br"))
    return currency.format(this)
        .replace("R$", "R$ ")
        .replace("-R$ ", "R$ -")
}