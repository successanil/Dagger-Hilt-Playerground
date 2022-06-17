package com.codingwithmitch.daggerhiltplayground.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.codingwithmitch.daggerhiltplayground.model.Blog
import com.codingwithmitch.daggerhiltplayground.repository.MainRepository
import com.codingwithmitch.daggerhiltplayground.util.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel
    @ViewModelInject
    constructor(
        private val mainRepository: MainRepository,
        @Assisted private val savedStateHandle: SavedStateHandle
    ): ViewModel() {
        private val _dataState : MutableLiveData<DataState<List<Blog>>> = MutableLiveData()
        val dataState : LiveData<DataState<List<Blog>>>
        get() = _dataState

        fun setStateEvent(mainstateEvent:MainStateEvent) {
            viewModelScope.launch {
                when(mainstateEvent) {
                    is MainStateEvent.GetBlogsEvents -> {
                        mainRepository.getBlog().onEach { dataState ->
                            _dataState.value = dataState
                        }
                            .launchIn(viewModelScope)
                    }
                    is MainStateEvent.None -> {
                        // who cares
                    }
                }

            }
        }



    }

sealed class MainStateEvent {
    object GetBlogsEvents : MainStateEvent()
    object None : MainStateEvent()
}
