package com.example.whoishakaton.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoishakaton.domain.models.DomainHistoryUIModel
import com.example.whoishakaton.domain.use_cases.local.AddNewSearchUseCase
import com.example.whoishakaton.domain.use_cases.local.RecentSearchesHomeUseCase
import com.example.whoishakaton.utils.Resource
import com.example.whoishakaton.utils.overlays_and_dialogs.GeneralEventsHandler
import com.example.whoishakaton.utils.overlays_and_dialogs.GeneralEventsHandlerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val addNewSearchUseCase: AddNewSearchUseCase,
    private val recentSearchesHomeUseCase: RecentSearchesHomeUseCase
) : ViewModel() {

    private val handler: GeneralEventsHandler = GeneralEventsHandlerProvider.generalEventsHandler

    private val _recentSearches = MutableLiveData<Resource<List<DomainHistoryUIModel>>>()
    val recentSearches: LiveData<Resource<List<DomainHistoryUIModel>>> = _recentSearches

    init {
        viewModelScope.launch {
            _recentSearches.value = recentSearchesHomeUseCase.execute()
        }
    }
}
