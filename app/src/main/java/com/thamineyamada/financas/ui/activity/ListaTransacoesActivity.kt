package com.thamineyamada.financas.ui.activity

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.thamineyamada.financas.R
import com.thamineyamada.financas.delegate.TransacaoDelegate
import com.thamineyamada.financas.model.Tipo
import com.thamineyamada.financas.model.Transacao
import com.thamineyamada.financas.ui.ResumoView
import com.thamineyamada.financas.ui.adapter.ListaTransacoesAdapter
import com.thamineyamada.financas.ui.dialog.AdicionaTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() {

    private val transacoes : MutableList<Transacao> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        configuraResumo()
        configuraLista()
        configuraFab()
    }

    private fun configuraFab() {
        lista_transacoes_adiciona_receita
            .setOnClickListener {
                chamaDialogDeAdicao(Tipo.RECEITA)
            }
        lista_transacoes_adiciona_despesa
            .setOnClickListener {
                chamaDialogDeAdicao(Tipo.DESPESA)
            }
    }

    private fun chamaDialogDeAdicao(tipo : Tipo) {
        AdicionaTransacaoDialog(window.decorView as ViewGroup, this)
            .configuraDialog(tipo, object : TransacaoDelegate {
                override fun delegate(transacao: Transacao) {
                    atualizaTransacoes(transacao)
                    lista_transacoes_adiciona_menu.close(true)
                }

            })
    }

    private fun atualizaTransacoes(transacao: Transacao) {
        transacoes.add(transacao)
        configuraLista()
        configuraResumo()
    }

    private fun configuraResumo() {
        val view: View = window.decorView
        val resumoView = ResumoView(this, view, transacoes)
        resumoView.atualiza()
    }

    private fun configuraLista() {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }

//    private fun transacoesDeExemplo(): List<Transacao> {
//        return listOf(
//            Transacao(valor = 20.5, categoria = "Comida", tipo = Tipo.DESPESA),
//            Transacao(valor = 100.0, categoria = "Economia", tipo = Tipo.RECEITA),
//            Transacao(valor = 150.0, categoria = "Prêmio", tipo = Tipo.RECEITA),
//            Transacao(valor = 800.0, categoria = "Aluguel", tipo = Tipo.DESPESA),
//        )
//    }

}