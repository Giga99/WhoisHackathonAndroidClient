package com.example.whoishakaton.ui.search_history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoishakaton.domain.models.DomainHistoryUIModel
import com.example.whoishakaton.domain.use_cases.local.SearchHistoryUseCase
import com.example.whoishakaton.utils.Resource
import com.example.whoishakaton.utils.overlays_and_dialogs.GeneralEventsHandler
import com.example.whoishakaton.utils.overlays_and_dialogs.GeneralEventsHandlerProvider
import com.example.whoishakaton.utils.overlays_and_dialogs.launchWithLoadingOverlay
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchHistoryViewModel @Inject constructor(
    private val searchHistoryUseCase: SearchHistoryUseCase
) : ViewModel() {

    private val handler: GeneralEventsHandler = GeneralEventsHandlerProvider.generalEventsHandler

    private val _searchHistory = MutableLiveData<Resource<List<DomainHistoryUIModel>>>()
    val searchHistory: LiveData<Resource<List<DomainHistoryUIModel>>> = _searchHistory

    init {
        viewModelScope.launchWithLoadingOverlay(handler) {
            _searchHistory.value = searchHistoryUseCase.execute()
        }
    }
}
