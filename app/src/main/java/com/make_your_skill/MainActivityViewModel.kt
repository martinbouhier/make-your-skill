package com.make_your_skill;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

class MainActivityViewModel: ViewModel() {

    companion object {

        val Factory: ViewModelProvider. Factory = viewModelFactory {
            initializer {
                MainActivityViewModel()
            }
        }
    }
}
