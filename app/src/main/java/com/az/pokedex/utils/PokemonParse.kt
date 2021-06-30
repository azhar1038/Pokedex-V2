package com.az.pokedex.utils

fun parsePokemonName(name: String): String{
    val subNames = name.split("-").toMutableList()
    for(i in subNames.indices){
        subNames[i] = subNames[i].replaceFirstChar { it.uppercaseChar() }
    }
    return subNames.joinToString(" ")
}