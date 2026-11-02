package com.example.intencionesimplicitas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Ejercicio2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio2)

        val listaProductos = listOf(
            Producto("Laptop Pro 15", R.drawable.ic_laptop),
            Producto("Smartphone X", R.drawable.ic_phone),
            Producto("Audífonos Inalámbricos", R.drawable.ic_headphones),
            Producto("Reloj Inteligente", R.drawable.ic_watch),
            Producto("Cámara Réflex 4K", R.drawable.ic_camera)
        )

        val rvProductos = findViewById<RecyclerView>(R.id.rvProductos)
        rvProductos.layoutManager = LinearLayoutManager(this)
        rvProductos.adapter = ProductosAdapter(listaProductos)
    }
}