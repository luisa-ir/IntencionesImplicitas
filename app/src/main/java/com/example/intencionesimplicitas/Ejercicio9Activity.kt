package com.example.intencionesimplicitas

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Ejercicio9Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placeholder)
        findViewById<TextView>(R.id.tvTituloPlaceholder).text = "Ejercicio 9: Compartir Texto"
    }
}