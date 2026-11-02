package com.example.intencionesimplicitas

import java.io.Serializable

data class Lugar(
    val nombre: String,
    val descripcion: String,
    val imagen: Int
) : Serializable