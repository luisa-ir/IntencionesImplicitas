package com.example.intencionesimplicitas

import java.io.Serializable

data class ProductoPrecio(
    val nombre: String,
    val precio: Double
) : Serializable