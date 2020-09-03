package com.thamineyamada.financas.extension

import java.text.SimpleDateFormat
import java.util.Calendar

fun Calendar.formataParaBrasileiro() : String {
    val formatoBrasileiro = "dd/MM/yyyy"
    val formata = SimpleDateFormat(formatoBrasileiro)
    return formata.format(this.time)
}