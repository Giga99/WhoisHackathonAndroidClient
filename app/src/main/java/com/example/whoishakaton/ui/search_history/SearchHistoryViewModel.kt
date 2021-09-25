package com.example.whoishakaton.ui.search_history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoishakaton.domain.models.DomainHistoryUIModel
import com.example.whoishakaton.domain.use_cases.SearchHistoryUseCase
import com.example.whoishakaton.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchHistoryViewModel @Inject constructor(
    private val searchHistoryUseCase: SearchHistoryUseCase
) : ViewModel() {

    private val _searchHistory = MutableLiveData<Resource<List<DomainHistoryUIModel>>>()
    val searchHistory: LiveData<Resource<List<DomainHistoryUIModel>>> = _searchHistory

    init {
        viewModelScope.launch {
            _searchHistory.value = searchHistoryUseCase.execute()
        }
    }
}
