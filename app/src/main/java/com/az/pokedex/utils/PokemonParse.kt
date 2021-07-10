package com.az.pokedex.utils

import androidx.compose.ui.graphics.Color
import com.az.pokedex.model.remote.ResponseStat
import com.az.pokedex.ui.theme.*

fun parseName(name: String): String {
    val subNames = name.split("-").toMutableList()
    for (i in subNames.indices) {
        subNames[i] = subNames[i].replaceFirstChar { it.uppercaseChar() }
    }
    return subNames.joinToString(" ")
}

fun parseStatToColor(stat: ResponseStat): Color {
    return when (stat.stat.name.lowercase()) {
        "hp" -> HPColor
        "attack" -> AtkColor
        "defense" -> DefColor
        "special-attack" -> SpAtkColor
        "special-defense" -> SpDefColor
        "speed" -> SpdColor
        else -> Color.White
    }
}

fun parseStatToAbbr(stat: ResponseStat): String {
    return when (stat.stat.name.lowercase()) {
        "hp" -> "HP"
        "attack" -> "Atk"
        "defense" -> "Def"
        "special-attack" -> "SpAtk"
        "special-defense" -> "SpDef"
        "speed" -> "Spd"
        else -> ""
    }
}