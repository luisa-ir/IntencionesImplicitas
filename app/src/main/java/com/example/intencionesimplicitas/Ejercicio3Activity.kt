package com.example.intencionesimplicitas

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Ejercicio3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio3)

        val listaLugares = listOf(
            Lugar(
                "Parque Nacional Natural",
                "Un hermoso parque de reserva natural con amplia biodiversidad, senderos ecológicos y zonas de avistamiento de aves.",
                R.drawable.ic_park
            ),
            Lugar(
                "Playa del Sol",
                "Playa de arenas blancas y aguas cristalinas, ideal para relajarse y disfrutar del sol y los deportes acuáticos.",
                R.drawable.ic_beach
            ),
            Lugar(
                "Montaña de los Andes",
                "Imponente cordillera montañosa ideal para senderismo, montañismo y contemplar vistas panorámicas espectaculares.",
                R.drawable.ic_mountain
            ),
            Lugar(
                "Museo de Historia y Arte",
                "Galería cultural que alberga importantes colecciones históricas, esculturas y pinturas emblemáticas.",
                R.drawable.ic_museum
            ),
            Lugar(
                "Castillo Medieval",
                "Fortaleza histórica del siglo XV con imponentes murallas, galerías y vistas estratégicas de la región.",
                R.drawable.ic_castle
            )
        )

        val rvLugares = findViewById<RecyclerView>(R.id.rvLugares)
        rvLugares.layoutManager = LinearLayoutManager(this)
        rvLugares.adapter = LugaresAdapter(listaLugares) { lugar ->
            val intent = Intent(this, DetalleLugarActivity::class.java).apply {
                putExtra(DetalleLugarActivity.EXTRA_NOMBRE, lugar.nombre)
                putExtra(DetalleLugarActivity.EXTRA_DESCRIPCION, lugar.descripcion)
                putExtra(DetalleLugarActivity.EXTRA_IMAGEN, lugar.imagen)
            }
            startActivity(intent)
        }
    }
}