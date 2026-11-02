package com.example.intencionesimplicitas

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Ejercicio4Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio4)

        val etNuevoItem = findViewById<EditText>(R.id.etNuevoItem)
        val btnAgregar = findViewById<Button>(R.id.btnAgregar)
        val rvItems = findViewById<RecyclerView>(R.id.rvItems)

        val listaInicial = mutableListOf(
            "Aprender Kotlin",
            "Configurar RecyclerView",
            "Implementar Adapters"
        )

        val adapter = ItemsAdapter(listaInicial) { itemSeleccionado ->
            Toast.makeText(this, "Seleccionado: $itemSeleccionado", Toast.LENGTH_SHORT).show()
        }

        rvItems.layoutManager = LinearLayoutManager(this)
        rvItems.adapter = adapter

        btnAgregar.setOnClickListener {
            val texto = etNuevoItem.text.toString().trim()
            if (texto.isNotEmpty()) {
                adapter.agregarItem(texto)
                etNuevoItem.text.clear()
                rvItems.smoothScrollToPosition(adapter.itemCount - 1)
            } else {
                Toast.makeText(this, "Por favor ingresa un texto", Toast.LENGTH_SHORT).show()
            }
        }
    }
}