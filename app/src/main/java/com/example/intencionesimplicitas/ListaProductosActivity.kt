package com.example.intencionesimplicitas

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListaProductosActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_LISTA_PRODUCTOS = "extra_lista_productos"
        const val EXTRA_PRODUCTO_SELECCIONADO = "extra_producto_seleccionado"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_productos)

        @Suppress("UNCHECKED_CAST", "DEPRECATION")
        val productos = (intent.getSerializableExtra(EXTRA_LISTA_PRODUCTOS) as? ArrayList<ProductoPrecio>)
            ?: arrayListOf()

        val rvListaProductos = findViewById<RecyclerView>(R.id.rvListaProductos)
        rvListaProductos.layoutManager = LinearLayoutManager(this)
        rvListaProductos.adapter = ProductosPrecioAdapter(productos) { productoSeleccionado ->
            val resultIntent = Intent().apply {
                putExtra(EXTRA_PRODUCTO_SELECCIONADO, productoSeleccionado)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}