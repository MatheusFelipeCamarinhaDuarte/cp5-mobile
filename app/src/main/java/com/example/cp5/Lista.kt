package com.example.cp5

import android.os.Bundle
import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aulasqlite.bancodedados.DatabaseHelper

class Lista : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var bancoDados: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla o layout para este fragmento
        return inflater.inflate(R.layout.fragment_lista, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializando o banco de dados
        bancoDados = DatabaseHelper(requireContext())

        // Configurando o RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewProdutos)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        listarProdutos()
    }

    private fun listarProdutos() {
        val listaProdutos = mutableListOf<Produto>()

        val sql = "SELECT * FROM ${DatabaseHelper.TABELA_PRODUTOS}"
        val cursor = bancoDados.readableDatabase.rawQuery(sql, null)

        val indiceProduto = cursor.getColumnIndex("${DatabaseHelper.ID_PRODUTO}")
        val indiceTitulo = cursor.getColumnIndex("${DatabaseHelper.TITULO}")
        val indiceDescricao = cursor.getColumnIndex("${DatabaseHelper.DESCRICAO}")
        val indiceValor = cursor.getColumnIndex("${DatabaseHelper.VALOR}")
        val indiceTipo = cursor.getColumnIndex("${DatabaseHelper.TIPO}")
        val indiceEstado = cursor.getColumnIndex("${DatabaseHelper.ESTADO}")

        while (cursor.moveToNext()) {
            val idProduto = cursor.getInt(indiceProduto)
            val titulo = cursor.getString(indiceTitulo)
            val descricao = cursor.getString(indiceDescricao)
            val valor = cursor.getFloat(indiceValor)
            val tipo = cursor.getString(indiceTipo)
            val estado = cursor.getString(indiceEstado)



            val produto = Produto(idProduto, titulo, descricao, valor, tipo, estado)
            listaProdutos.add(produto)
        }

        cursor.close()

        // Populando o RecyclerView com a lista de produtos
        recyclerView.adapter = ProdutoAdapter(listaProdutos)
    }
}
