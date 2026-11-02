package com.example.intencionesimplicitas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Ejercicio1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio1)

        val nombres = listOf(
            "Carlos Gómez",
            "María Rodríguez",
            "Juan Pérez",
            "Ana Martínez",
            "Luis Fernández",
            "Sofía López",
            "Diego Morales",
            "Valentina Torres",
            "Andrés Castro",
            "Camila Vargas"
        )

        val rvNombres = findViewById<RecyclerView>(R.id.rvNombres)
        rvNombres.layoutManager = LinearLayoutManager(this)
        rvNombres.adapter = NombresAdapter(nombres)
    }
}