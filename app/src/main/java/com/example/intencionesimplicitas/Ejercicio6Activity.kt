package com.example.intencionesimplicitas

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Ejercicio6Activity : AppCompatActivity() {

    private lateinit var adapter: ContactosAdapter

    private val nuevoContactoLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            @Suppress("DEPRECATION")
            val nuevoContacto = result.data?.getSerializableExtra(
                EditarContactoActivity.EXTRA_CONTACTO
            ) as? Contacto

            if (nuevoContacto != null) {
                adapter.agregarContacto(nuevoContacto)
            }
        }
    }

    private val detalleContactoLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val posicion = result.data?.getIntExtra(DetalleContactoActivity.EXTRA_POSICION, -1) ?: -1

        when (result.resultCode) {
            RESULT_OK -> {
                @Suppress("DEPRECATION")
                val contactoActualizado = result.data?.getSerializableExtra(
                    DetalleContactoActivity.EXTRA_CONTACTO
                ) as? Contacto

                if (contactoActualizado != null && posicion != -1) {
                    adapter.actualizarContacto(posicion, contactoActualizado)
                }
            }
            DetalleContactoActivity.RESULT_DELETE -> {
                if (posicion != -1) {
                    adapter.eliminarContacto(posicion)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio6)

        val listaInicialContactos = mutableListOf(
            Contacto(1, "Carlos Mendoza", "+57 300 123 4567"),
            Contacto(2, "Ana María Silva", "+57 310 987 6543"),
            Contacto(3, "Roberto Gómez", "+57 320 555 1234")
        )

        adapter = ContactosAdapter(listaInicialContactos) { contacto, posicion ->
            val intent = Intent(this, DetalleContactoActivity::class.java).apply {
                putExtra(DetalleContactoActivity.EXTRA_CONTACTO, contacto)
                putExtra(DetalleContactoActivity.EXTRA_POSICION, posicion)
            }
            detalleContactoLauncher.launch(intent)
        }

        val rvContactos = findViewById<RecyclerView>(R.id.rvContactos)
        rvContactos.layoutManager = LinearLayoutManager(this)
        rvContactos.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_ejercicio6, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_nuevo_contacto -> {
                val intent = Intent(this, EditarContactoActivity::class.java)
                nuevoContactoLauncher.launch(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}