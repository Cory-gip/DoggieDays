package com.example.phase3project2practice.repositories

import androidx.lifecycle.LiveData
import com.example.phase3project2practice.database.DogDao
import com.example.phase3project2practice.database.DogImage
import com.example.phase3project2practice.network.Dog
import com.example.phase3project2practice.network.DogPicApiService
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val dogDao: DogDao,
    private val dogPicApiService: DogPicApiService
) {
    suspend fun insertDog(dogImage: DogImage) = dogDao.insertDog(dogImage)

    suspend fun deleteDog(dogImage: DogImage) = dogDao.deleteDog(dogImage)

    suspend fun getRandomDog(): Dog {
        return dogPicApiService.getRandomDog()
    }

    fun getDogs(): LiveData<List<DogImage>> = dogDao.getDogs()

}