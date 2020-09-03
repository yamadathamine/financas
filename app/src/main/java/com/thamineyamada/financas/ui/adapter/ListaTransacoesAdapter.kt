package com.thamineyamada.financas.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import com.thamineyamada.financas.R
import com.thamineyamada.financas.extension.formataParaBrasileiro
import com.thamineyamada.financas.extension.limitaEmAte
import com.thamineyamada.financas.model.Tipo
import com.thamineyamada.financas.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*

class ListaTransacoesAdapter(private val transacoes: List<Transacao>,
                             private val context: Context): BaseAdapter()  {

    private val LIMITE_TAMANHO_CATEGORIA = 14

    override fun getCount(): Int {
        return transacoes.size
    }

    override fun getItem(posicao: Int): Transacao {
        return transacoes[posicao]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(posicao: Int, view: View?, parent: ViewGroup?): View {
        val viewCriada = LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)

        val transacao = transacoes[posicao]

        adicionaValor(transacao, viewCriada)
        adicionarIcone(transacao, viewCriada)
        adicionaCategoria(viewCriada, transacao)
        adicionaData(viewCriada, transacao)

        return viewCriada
    }

    private fun adicionaData( viewCriada: View, transacao: Transacao) {
        viewCriada.transacao_data.text = transacao.data.formataParaBrasileiro()
    }

    private fun adicionaCategoria( viewCriada: View,
                                   transacao: Transacao) {
        viewCriada.transacao_categoria.text =
            transacao.categoria.limitaEmAte(LIMITE_TAMANHO_CATEGORIA)
    }

    private fun adicionarIcone( transacao: Transacao, viewCriada: View) {
        viewCriada.transacao_icone.setBackgroundResource(iconePor(transacao.tipo))
    }

    private fun iconePor(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return  R.drawable.icone_transacao_item_receita
        }
        return  R.drawable.icone_transacao_item_despesa
    }

    private fun adicionaValor( transacao: Transacao, viewCriada: View) {
        viewCriada.transacao_valor.setTextColor(corPor(transacao.tipo))
        viewCriada.transacao_valor.text = transacao.valor.formataParaBrasileiro()
    }

    private fun corPor(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return ContextCompat.getColor(context, R.color.receita)
        }
        return ContextCompat.getColor(context, R.color.despesa)
    }
}