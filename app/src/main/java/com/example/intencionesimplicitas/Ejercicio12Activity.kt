package com.example.intencionesimplicitas

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView

class Ejercicio12Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio12)

        findViewById<MaterialCardView>(R.id.cardLlamar).setOnClickListener {
            llamarNumero("3001234567")
        }

        findViewById<MaterialCardView>(R.id.cardCorreo).setOnClickListener {
            enviarCorreo("contacto@ejemplo.com", "Consulta sobre Android", "Hola, me gustaría recibir más información.")
        }

        findViewById<MaterialCardView>(R.id.cardMapa).setOnClickListener {
            abrirUbicacionMapa("4.60971", "-74.08175", "Plaza de Bolívar, Bogotá")
        }

        findViewById<MaterialCardView>(R.id.cardCompartir).setOnClickListener {
            compartirTexto("¡Hola! Estoy probando la aplicación de intenciones implícitas en Android.")
        }

        findViewById<MaterialCardView>(R.id.cardWeb).setOnClickListener {
            abrirPaginaWeb("https://developer.android.com")
        }
    }

    private fun llamarNumero(numero: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$numero")
        }
        ejecutarIntencion(intent, "No hay aplicación disponible para realizar llamadas")
    }

    private fun enviarCorreo(email: String, asunto: String, mensaje: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$email?subject=${Uri.encode(asunto)}&body=${Uri.encode(mensaje)}")
        }
        ejecutarIntencion(intent, "No hay aplicación de correo electrónico instalada")
    }

    private fun abrirUbicacionMapa(lat: String, lng: String, etiqueta: String) {
        val uriGeo = Uri.parse("geo:$lat,$lng?q=$lat,$lng(${Uri.encode(etiqueta)})")
        val intent = Intent(Intent.ACTION_VIEW, uriGeo)
        ejecutarIntencion(intent, "No hay aplicación de mapas instalada")
    }

    private fun compartirTexto(texto: String) {
        val intentCompartir = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, texto)
        }
        val chooser = Intent.createChooser(intentCompartir, "Compartir texto con:")
        ejecutarIntencion(chooser, "No se encontró ninguna aplicación para compartir")
    }

    private fun abrirPaginaWeb(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        ejecutarIntencion(intent, "No hay ningún navegador web disponible")
    }

    private fun ejecutarIntencion(intent: Intent, mensajeError: String) {
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, mensajeError, Toast.LENGTH_SHORT).show()
        }
    }
}