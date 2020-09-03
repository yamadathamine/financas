package com.thamineyamada.financas.ui

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.thamineyamada.financas.R
import com.thamineyamada.financas.extension.formataParaBrasileiro
import com.thamineyamada.financas.model.Resumo
import com.thamineyamada.financas.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*

class ResumoView(private val context : Context,
                 private val view : View,
                 transacoes : List<Transacao>) {

    private val resumo : Resumo = Resumo(transacoes)
    private val corReceita = ContextCompat.getColor(context, R.color.receita)
    private val corDespesa = ContextCompat.getColor(context, R.color.despesa)

    fun atualiza(){
        adicionaReceita()
        adicionaDespesa()
        adicionaTotal()
    }

    private fun adicionaReceita() {
        val totalReceitas = resumo.receita
        with(view.resumo_card_receita){
            text = totalReceitas.formataParaBrasileiro()
            setTextColor(corReceita)
        }
    }

    private fun adicionaDespesa() {
        val totalDespesa = resumo.despesa
        with(view.resumo_card_despesa){
            text = totalDespesa.formataParaBrasileiro()
            setTextColor(corDespesa)
        }
    }

    private fun adicionaTotal(){
        val total = resumo.total
        with(view.resumo_card_total) {
            text = total.formataParaBrasileiro()
            setTextColor(corPor(total))
        }
    }

    fun corPor(valor : Double) : Int{
        if (valor >= 0) {
            return corReceita
        }
        return corDespesa
    }

}