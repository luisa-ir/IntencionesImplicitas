package com.example.intencionesimplicitas

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Ejercicio8Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio8)

        val etLatitud = findViewById<EditText>(R.id.etLatitud)
        val etLongitud = findViewById<EditText>(R.id.etLongitud)
        val etEtiqueta = findViewById<EditText>(R.id.etEtiqueta)
        val btnMostrarUbicacion = findViewById<Button>(R.id.btnMostrarUbicacion)

        btnMostrarUbicacion.setOnClickListener {
            val lat = etLatitud.text.toString().trim()
            val lng = etLongitud.text.toString().trim()
            val etiqueta = etEtiqueta.text.toString().trim()

            if (lat.isEmpty() || lng.isEmpty()) {
                Toast.makeText(this, "Por favor ingrese latitud y longitud válidas", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val uriGeo = Uri.parse("geo:$lat,$lng?q=$lat,$lng(${Uri.encode(etiqueta)})")
            val intentMapa = Intent(Intent.ACTION_VIEW, uriGeo)

            try {
                startActivity(intentMapa)
            } catch (e: ActivityNotFoundException) {
                val webUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=$lat,$lng")
                val intentWeb = Intent(Intent.ACTION_VIEW, webUri)
                try {
                    startActivity(intentWeb)
                } catch (ex: Exception) {
                    Toast.makeText(this, "No hay aplicación disponible para abrir mapas", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}