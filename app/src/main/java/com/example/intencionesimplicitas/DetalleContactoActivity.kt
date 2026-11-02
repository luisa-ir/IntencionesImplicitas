package com.example.intencionesimplicitas

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class DetalleContactoActivity : AppCompatActivity() {

    companion object {
        const val RESULT_DELETE = 100
        const val EXTRA_CONTACTO = "extra_contacto"
        const val EXTRA_POSICION = "extra_posicion"
    }

    private lateinit var tvNombreDetalleContacto: TextView
    private lateinit var tvTelefonoDetalleContacto: TextView

    private var contacto: Contacto? = null
    private var posicion: Int = -1

    private val editarContactoLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            @Suppress("DEPRECATION")
            val contactoActualizado = result.data?.getSerializableExtra(
                EditarContactoActivity.EXTRA_CONTACTO
            ) as? Contacto

            if (contactoActualizado != null) {
                contacto = contactoActualizado
                tvNombreDetalleContacto.text = contactoActualizado.nombre
                tvTelefonoDetalleContacto.text = contactoActualizado.telefono

                val resultIntent = Intent().apply {
                    putExtra(EXTRA_CONTACTO, contactoActualizado)
                    putExtra(EXTRA_POSICION, posicion)
                }
                setResult(RESULT_OK, resultIntent)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_contacto)

        tvNombreDetalleContacto = findViewById(R.id.tvNombreDetalleContacto)
        tvTelefonoDetalleContacto = findViewById(R.id.tvTelefonoDetalleContacto)

        @Suppress("DEPRECATION")
        contacto = intent.getSerializableExtra(EXTRA_CONTACTO) as? Contacto
        posicion = intent.getIntExtra(EXTRA_POSICION, -1)

        contacto?.let {
            tvNombreDetalleContacto.text = it.nombre
            tvTelefonoDetalleContacto.text = it.telefono
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalle_contacto, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_editar_contacto -> {
                contacto?.let {
                    val intent = Intent(this, EditarContactoActivity::class.java).apply {
                        putExtra(EditarContactoActivity.EXTRA_CONTACTO, it)
                        putExtra(EditarContactoActivity.EXTRA_POSICION, posicion)
                    }
                    editarContactoLauncher.launch(intent)
                }
                true
            }
            R.id.action_eliminar_contacto -> {
                val resultIntent = Intent().apply {
                    putExtra(EXTRA_POSICION, posicion)
                }
                setResult(RESULT_DELETE, resultIntent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}