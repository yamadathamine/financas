package com.thamineyamada.financas.extension

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


fun String.limitaEmAte(caracteres : Int) : String {
    if (this.length > caracteres){
        return "${this.substring(0, 14)}..."
    }
    return this
}

fun String.converteParaData(): Calendar {
    val formatoBrasileiro = SimpleDateFormat("dd/MM/yyyy")
    val dataConvertida: Date = formatoBrasileiro.parse(this)
    val data = Calendar.getInstance()
    data.time = dataConvertida
    return data
}