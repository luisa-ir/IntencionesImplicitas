package com.example.intencionesimplicitas

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Ejercicio7Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio7)

        val etTituloNota = findViewById<EditText>(R.id.etTituloNota)
        val etContenidoNota = findViewById<EditText>(R.id.etContenidoNota)
        val btnAgregarNota = findViewById<Button>(R.id.btnAgregarNota)
        val rvNotas = findViewById<RecyclerView>(R.id.rvNotas)

        val listaNotas = mutableListOf(
            Nota(1, "Reunión de proyecto", "Revisar los requerimientos de la entrega final.", "02/11/2026"),
            Nota(2, "Lista de compras", "Comprar frutas, leche, café y pan integral.", "01/11/2026")
        )

        val adapter = NotasAdapter(listaNotas) { nota ->
            Toast.makeText(this, "Nota: ${nota.titulo}", Toast.LENGTH_SHORT).show()
        }

        rvNotas.layoutManager = LinearLayoutManager(this)
        rvNotas.adapter = adapter

        btnAgregarNota.setOnClickListener {
            val titulo = etTituloNota.text.toString().trim()
            val contenido = etContenidoNota.text.toString().trim()

            if (titulo.isEmpty() || contenido.isEmpty()) {
                Toast.makeText(this, "Por favor completa título y contenido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val fechaActual = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
            val nuevaNota = Nota(System.currentTimeMillis(), titulo, contenido, fechaActual)

            adapter.agregarNota(nuevaNota)
            etTituloNota.text.clear()
            etContenidoNota.text.clear()
            rvNotas.smoothScrollToPosition(0)

            Toast.makeText(this, "Nota agregada con éxito", Toast.LENGTH_SHORT).show()
        }
    }
}