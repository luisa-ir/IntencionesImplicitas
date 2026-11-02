package com.example.intencionesimplicitas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnEjercicio1).setOnClickListener {
            abrirEjercicio(Ejercicio1Activity::class.java)
        }

        findViewById<Button>(R.id.btnEjercicio2).setOnClickListener {
            abrirEjercicio(Ejercicio2Activity::class.java)
        }

        findViewById<Button>(R.id.btnEjercicio3).setOnClickListener {
            abrirEjercicio(Ejercicio3Activity::class.java)
        }

        findViewById<Button>(R.id.btnEjercicio4).setOnClickListener {
            abrirEjercicio(Ejercicio4Activity::class.java)
        }

        findViewById<Button>(R.id.btnEjercicio5).setOnClickListener {
            abrirEjercicio(Ejercicio5Activity::class.java)
        }

        findViewById<Button>(R.id.btnEjercicio6).setOnClickListener {
            abrirEjercicio(Ejercicio6Activity::class.java)
        }

        findViewById<Button>(R.id.btnEjercicio7).setOnClickListener {
            abrirEjercicio(Ejercicio7Activity::class.java)
        }

        findViewById<Button>(R.id.btnEjercicio8).setOnClickListener {
            abrirEjercicio(Ejercicio8Activity::class.java)
        }

        findViewById<Button>(R.id.btnEjercicio9).setOnClickListener {
            abrirEjercicio(Ejercicio9Activity::class.java)
        }

        findViewById<Button>(R.id.btnEjercicio10).setOnClickListener {
            abrirEjercicio(Ejercicio10Activity::class.java)
        }

        findViewById<Button>(R.id.btnEjercicio11).setOnClickListener {
            abrirEjercicio(Ejercicio11Activity::class.java)
        }

        findViewById<Button>(R.id.btnEjercicio12).setOnClickListener {
            abrirEjercicio(Ejercicio12Activity::class.java)
        }
    }

    private fun abrirEjercicio(
        actividad: Class<out AppCompatActivity>
    ) {
        val intent = Intent(this, actividad)
        startActivity(intent)
    }
}