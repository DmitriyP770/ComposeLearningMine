package com.example.composelearning

import android.app.Application
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainVM(application: Application) : AndroidViewModel(application) {

    private var counter: MutableLiveData<Int> = MutableLiveData(0)


    fun incrementCounter(){
        counter.postValue(counter.value?.plus(1) ?: 0)
    }
    fun getValue():LiveData<Int>{
        return counter
    }
}


