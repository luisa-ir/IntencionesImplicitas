package com.example.intencionesimplicitas

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class Ejercicio5Activity : AppCompatActivity() {

    private lateinit var tvProductoSeleccionado: TextView

    private val seleccionarProductoLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            @Suppress("DEPRECATION")
            val producto = result.data?.getSerializableExtra(
                ListaProductosActivity.EXTRA_PRODUCTO_SELECCIONADO
            ) as? ProductoPrecio

            if (producto != null) {
                tvProductoSeleccionado.text = String.format(
                    Locale.getDefault(),
                    "%s - $%.2f",
                    producto.nombre,
                    producto.precio
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio5)

        tvProductoSeleccionado = findViewById(R.id.tvProductoSeleccionado)
        val btnSeleccionarProducto = findViewById<Button>(R.id.btnSeleccionarProducto)

        val listaProductos = arrayListOf(
            ProductoPrecio("Computador Portátil", 1200.00),
            ProductoPrecio("Teléfono Inteligente", 850.50),
            ProductoPrecio("Teclado Mecánico", 95.99),
            ProductoPrecio("Monitor 4K 27\"", 350.00),
            ProductoPrecio("Mouse Inalámbrico", 45.00),
            ProductoPrecio("Audífonos Gaming", 110.00)
        )

        btnSeleccionarProducto.setOnClickListener {
            val intent = Intent(this, ListaProductosActivity::class.java).apply {
                putExtra(ListaProductosActivity.EXTRA_LISTA_PRODUCTOS, listaProductos)
            }
            seleccionarProductoLauncher.launch(intent)
        }
    }
}