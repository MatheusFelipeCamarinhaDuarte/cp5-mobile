package com.example.cp5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProdutoAdapter(private val listaProdutos: List<Produto>) : RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder>() {

    class ProdutoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titulo: TextView = view.findViewById(R.id.textViewTitulo)
        val descricao: TextView = view.findViewById(R.id.textViewDescricao)
        val valor: TextView = view.findViewById(R.id.textViewValor)
        val tipo: TextView = view.findViewById(R.id.textViewTipo)
        val estado: TextView = view.findViewById(R.id.textViewEstado)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_produto, parent, false)
        return ProdutoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        val produto = listaProdutos[position]
        holder.titulo.text = produto.titulo
        holder.descricao.text = produto.descricao
        holder.valor.text = produto.valor.toString() // Converte o valor para String
        holder.tipo.text = produto.tipo
        holder.estado.text = produto.estado
    }

    override fun getItemCount() = listaProdutos.size
}
