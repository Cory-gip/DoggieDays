package com.example.phase3project2practice.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface DogDao {
    @Insert
    suspend fun insertDog(dog: DogImage)

    @Delete
    suspend fun deleteDog(dog: DogImage)

    @Query("SELECT * FROM dogs")
    fun getDogs(): LiveData<List<DogImage>>
}