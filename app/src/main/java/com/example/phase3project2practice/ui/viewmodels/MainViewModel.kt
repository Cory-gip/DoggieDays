package com.example.phase3project2practice.ui.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phase3project2practice.database.DogImage
import com.example.phase3project2practice.network.Dog
import com.example.phase3project2practice.repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {


    private val searchedDogs: MutableList<Dog> = mutableListOf()
    private val _searchHistory = MutableLiveData<MutableList<Dog>>()
    val searchHistory: LiveData<MutableList<Dog>> = _searchHistory

    private var index: Int
    val currentlyDisplayedDogIndex = MutableLiveData<Int>()

    init {
        index = 0
        getRandomDog()
    }

    fun getRandomDog() {
        viewModelScope.launch {
            val newDog = mainRepository.getRandomDog()
            searchedDogs.add(newDog)
            _searchHistory.value = searchedDogs
            currentlyDisplayedDogIndex.value = searchedDogs.size - 1
            index = currentlyDisplayedDogIndex.value!!
        }

    }

    fun displayNextDogInHistory() {
        index++
        when {
            index == searchedDogs.size -> {

                index = searchedDogs.size - 1
            }
            index > searchedDogs.size -> {
                index = searchedDogs.size - 1
            }
            else -> {
                currentlyDisplayedDogIndex.value = index
            }
        }
    }

    fun displayPreviousDogInHistory() {
        when {
            index <= 0 -> {
                return
            }
            else -> {
                index--
                currentlyDisplayedDogIndex.value = index
            }
        }
    }

    fun saveDog() {
        viewModelScope.launch {
            currentlyDisplayedDogIndex.value?.let { index ->
                _searchHistory.value?.get(index)?.let {
                    mainRepository.insertDog(DogImage(it.message))
                }
            }
        }
    }

    fun getDogs(): LiveData<List<DogImage>> {
        return mainRepository.getDogs()
    }


}