package com.example.intencionesimplicitas

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditarContactoActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_CONTACTO = "extra_contacto"
        const val EXTRA_POSICION = "extra_posicion"
    }

    private var contactoExistente: Contacto? = null
    private var posicion: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_contacto)

        val tvTituloEditarContacto = findViewById<TextView>(R.id.tvTituloEditarContacto)
        val etNombreContacto = findViewById<EditText>(R.id.etNombreContacto)
        val etTelefonoContacto = findViewById<EditText>(R.id.etTelefonoContacto)
        val btnGuardarContacto = findViewById<Button>(R.id.btnGuardarContacto)

        @Suppress("DEPRECATION")
        contactoExistente = intent.getSerializableExtra(EXTRA_CONTACTO) as? Contacto
        posicion = intent.getIntExtra(EXTRA_POSICION, -1)

        if (contactoExistente != null) {
            tvTituloEditarContacto.text = "Editar Contacto"
            etNombreContacto.setText(contactoExistente?.nombre)
            etTelefonoContacto.setText(contactoExistente?.telefono)
        } else {
            tvTituloEditarContacto.text = "Nuevo Contacto"
        }

        btnGuardarContacto.setOnClickListener {
            val nombre = etNombreContacto.text.toString().trim()
            val telefono = etTelefonoContacto.text.toString().trim()

            if (nombre.isEmpty() || telefono.isEmpty()) {
                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val id = contactoExistente?.id ?: System.currentTimeMillis()
            val contactoGuardado = Contacto(id, nombre, telefono)

            val resultIntent = Intent().apply {
                putExtra(EXTRA_CONTACTO, contactoGuardado)
                putExtra(EXTRA_POSICION, posicion)
            }

            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}