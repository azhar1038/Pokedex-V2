package com.az.pokedex.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.az.pokedex.model.remote.ResponseResult

@Entity(tableName = "pokemon_list")
data class PokemonProfile(
    @PrimaryKey()
    val id: Long,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "image_url")
    val imageUrl: String,

    @ColumnInfo(name = "favorite")
    val favorite: Boolean,
)
