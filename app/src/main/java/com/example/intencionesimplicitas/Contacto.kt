package com.example.intencionesimplicitas

import java.io.Serializable

data class Contacto(
    val id: Long,
    var nombre: String,
    var telefono: String
) : Serializable