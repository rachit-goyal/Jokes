package com.example.myapplication.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.Jokes
import com.example.myapplication.domain.repository.JokeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(val repository: JokeRepository):ViewModel() {

    val jokes : LiveData<Jokes>
        get() = repository.jokes
    val jokesList:LiveData<List<Jokes>>
        get() = repository.jokesList
   fun getData(){
       viewModelScope.launch {
           repository.getJokes()
       }
   }
    fun addJokesLocally(list: List<Jokes>){
        viewModelScope.launch {
            repository.addJokes(list)
        }
    }

    fun deleteData(){
        viewModelScope.launch{
            repository.deleteData()
        }
    }

    fun getLocalData(){
        viewModelScope.launch {
            repository.getLocalData()
        }
    }


}