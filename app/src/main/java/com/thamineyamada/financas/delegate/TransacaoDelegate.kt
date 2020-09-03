package com.thamineyamada.financas.delegate

import com.thamineyamada.financas.model.Transacao

interface TransacaoDelegate {
    fun delegate(transacao: Transacao)
}