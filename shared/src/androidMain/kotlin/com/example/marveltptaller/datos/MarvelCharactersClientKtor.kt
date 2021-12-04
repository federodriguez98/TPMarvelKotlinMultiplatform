package com.example.marveltptaller.datos

import com.example.marveltptaller.Modelo.Character
import kotlinx.serialization.Serializable

@Serializable
class DataMarvel(
    val results: List<Character>
)

@Serializable
class ResponseMarvel(
    val code: Int,
    val data: DataMarvel,
)