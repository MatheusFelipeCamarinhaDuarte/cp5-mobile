package com.example.cp5

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.example.aulasqlite.bancodedados.DatabaseHelper
import com.google.android.material.textfield.TextInputEditText

class Cadastro : Fragment() {
    private lateinit var spinnerTipo: Spinner
    private lateinit var spinnerEstado: Spinner

    private lateinit var btnSalvar: Button

    private lateinit var inputNome: TextInputEditText
    private lateinit var inputDescricao: TextInputEditText
    private lateinit var inputValor: TextInputEditText

    private lateinit var inputTipo: Spinner
    private lateinit var inputEstado: Spinner

    private lateinit var escrita: SQLiteDatabase


    override fun onAttach(context: Context) {
        super.onAttach(context)
        escrita = DatabaseHelper(context).writableDatabase

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_cadastro, container, false)

        btnSalvar = view.findViewById(R.id.btnEnviar)
        inputNome = view.findViewById(R.id.inputNome2)
        inputDescricao = view.findViewById(R.id.inputDescricao2)
        inputValor = view.findViewById(R.id.inputValor2)

        inputTipo = view.findViewById(R.id.spinner)
        inputEstado = view.findViewById(R.id.spinnerEstado)

        btnSalvar.setOnClickListener {
            salvar()
        }
        spinnerExibicao(view)
        spinnerExibicao2(view)


        return view
    }

    private fun spinnerExibicao(view: View) {
        val tiposDeProdutos = listOf("Tênis", "Camisetas", "Calças", "Jaquetas", "Roupa íntima")
        spinnerTipo = view.findViewById(R.id.spinner)
        spinnerTipo.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            tiposDeProdutos
        )
    }


    private fun spinnerExibicao2(view: View) {
        val listaDeEstados = listOf("Novo", "Bom", "Usado", "Velho")
        spinnerEstado = view.findViewById(R.id.spinnerEstado)
        spinnerEstado.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            listaDeEstados
        )
    }

    fun salvar() {
        val nomeProduto = inputNome.text.toString()
        val descricaoProduto = inputDescricao.text.toString()
        val valorProduto = inputValor.text.toString()
        val tipoProduto = inputTipo.selectedItem.toString()
        val estadoProduto = inputEstado.selectedItem.toString()

        try {
            val sql = "INSERT INTO produtos VALUES(null,'$nomeProduto','$descricaoProduto','$valorProduto','$tipoProduto','$estadoProduto');"
            escrita.execSQL(sql)
            Log.i("db_info", "Registro salvo")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
