package com.example.intencionesimplicitas

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetalleLugarActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NOMBRE = "extra_nombre"
        const val EXTRA_DESCRIPCION = "extra_descripcion"
        const val EXTRA_IMAGEN = "extra_imagen"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_lugar)

        val imgDetalleLugar = findViewById<ImageView>(R.id.imgDetalleLugar)
        val tvNombreDetalleLugar = findViewById<TextView>(R.id.tvNombreDetalleLugar)
        val tvDescripcionDetalleLugar = findViewById<TextView>(R.id.tvDescripcionDetalleLugar)
        val btnVolver = findViewById<Button>(R.id.btnVolver)

        val nombre = intent.getStringExtra(EXTRA_NOMBRE) ?: "Sin Nombre"
        val descripcion = intent.getStringExtra(EXTRA_DESCRIPCION) ?: "Sin Descripción"
        val imagen = intent.getIntExtra(EXTRA_IMAGEN, R.drawable.ic_park)

        tvNombreDetalleLugar.text = nombre
        tvDescripcionDetalleLugar.text = descripcion
        imgDetalleLugar.setImageResource(imagen)

        btnVolver.setOnClickListener {
            finish()
        }
    }
}