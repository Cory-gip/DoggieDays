package com.example.phase3project2practice.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DogImage::class], version = 1)
abstract class DogDatabase : RoomDatabase() {

    abstract fun getDogDao(): DogDao

}