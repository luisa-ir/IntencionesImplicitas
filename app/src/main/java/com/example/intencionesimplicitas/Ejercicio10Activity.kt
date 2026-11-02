package com.example.intencionesimplicitas

import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class Ejercicio10Activity : AppCompatActivity() {

    private lateinit var tvContactoSeleccionado: TextView

    // Contrato estándar para seleccionar un contacto de la lista de contactos del dispositivo
    private val pickContactLauncher = registerForActivityResult(
        ActivityResultContracts.PickContact()
    ) { uri: Uri? ->
        if (uri != null) {
            obtenerNombreContacto(uri)
        } else {
            Toast.makeText(this, "No se seleccionó ningún contacto", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio10)

        val btnSeleccionarContacto = findViewById<Button>(R.id.btnSeleccionarContacto)
        tvContactoSeleccionado = findViewById(R.id.tvContactoSeleccionado)

        btnSeleccionarContacto.setOnClickListener {
            pickContactLauncher.launch(null)
        }
    }

    private fun obtenerNombreContacto(contactUri: Uri) {
        val projection = arrayOf(ContactsContract.Contacts.DISPLAY_NAME)
        try {
            contentResolver.query(contactUri, projection, null, null, null)?.use { cursor ->
                if (cursor.moveToFirst()) {
                    val nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
                    if (nameIndex != -1) {
                        val nombre = cursor.getString(nameIndex)
                        tvContactoSeleccionado.text = nombre
                    } else {
                        tvContactoSeleccionado.text = "Nombre no disponible"
                    }
                }
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Error al obtener el contacto: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}