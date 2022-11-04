package com.mezencevsem.domain.model

data class Currency(
    val code: String,
    val name: String,
    val rate: Float? = null,
    val isFavorite: Boolean
)