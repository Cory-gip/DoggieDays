@file:Suppress("DataClassPrivateConstructor")

package com.example.phase3project2practice.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "dogs")
data class DogImage(
    val dogImageURL: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
