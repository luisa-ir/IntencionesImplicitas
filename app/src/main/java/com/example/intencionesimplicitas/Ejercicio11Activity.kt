package com.example.intencionesimplicitas

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class Ejercicio11Activity : AppCompatActivity() {

    private lateinit var tvNombreArchivoPdf: TextView
    private var pdfUriSeleccionado: Uri? = null

    private val seleccionarPdfLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            pdfUriSeleccionado = uri
            val nombreArchivo = uri.lastPathSegment ?: "Archivo PDF seleccionado"
            tvNombreArchivoPdf.text = "PDF: $nombreArchivo"
            abrirPdfConAppExterna(uri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio11)

        tvNombreArchivoPdf = findViewById(R.id.tvNombreArchivoPdf)
        val btnAbrirArchivo = findViewById<Button>(R.id.btnAbrirArchivo)

        btnAbrirArchivo.setOnClickListener {
            if (pdfUriSeleccionado != null) {
                abrirPdfConAppExterna(pdfUriSeleccionado!!)
            } else {
                seleccionarPdfLauncher.launch("application/pdf")
            }
        }
    }

    private fun abrirPdfConAppExterna(uri: Uri) {
        val intentPdf = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(uri, "application/pdf")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        try {
            val chooser = Intent.createChooser(intentPdf, "Abrir PDF con:")
            startActivity(chooser)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "No hay ningún visor de PDF instalado (como Google Drive o Adobe Reader)", Toast.LENGTH_LONG).show()
        }
    }
}