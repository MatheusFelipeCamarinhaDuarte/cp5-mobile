package com.example.cp5

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aulasqlite.bancodedados.DatabaseHelper

class MainActivity : AppCompatActivity() {

    private val bancoDados by lazy {
        DatabaseHelper(this)
    }
    private lateinit var btnCadastro:Button
    private lateinit var btnListar:Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        btnCadastro = findViewById(R.id.btnCadastrar)
        btnListar = findViewById(R.id.btnListar)

        btnListar.setOnClickListener {
            val listaFragment = Lista()
            supportFragmentManager.beginTransaction()
                .replace(R.id.conteinerDoFragmento, Lista())
                .commit()
        }



        btnCadastro.setOnClickListener{
            supportFragmentManager.beginTransaction()
                .replace(R.id.conteinerDoFragmento,Cadastro())
                .commit()
        }

    }





}