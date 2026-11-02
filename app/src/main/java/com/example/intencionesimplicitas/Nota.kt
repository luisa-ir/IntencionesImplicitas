package com.example.intencionesimplicitas

import java.io.Serializable

data class Nota(
    val id: Long,
    var titulo: String,
    var contenido: String,
    var fecha: String
) : Serializable