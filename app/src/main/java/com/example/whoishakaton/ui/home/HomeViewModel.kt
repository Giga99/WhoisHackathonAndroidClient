package com.example.whoishakaton.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoishakaton.domain.models.DomainHistoryUIModel
import com.example.whoishakaton.domain.models.DomainInformationUIModel
import com.example.whoishakaton.domain.use_cases.local.RecentSearchesHomeUseCase
import com.example.whoishakaton.domain.use_cases.remote.GetPopularDomainsUseCase
import com.example.whoishakaton.utils.Resource
import com.example.whoishakaton.utils.overlays_and_dialogs.GeneralEventsHandler
import com.example.whoishakaton.utils.overlays_and_dialogs.GeneralEventsHandlerProvider
import com.example.whoishakaton.utils.overlays_and_dialogs.launchWithLoadingOverlay
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val recentSearchesHomeUseCase: RecentSearchesHomeUseCase,
    private val getPopularDomainsUseCase: GetPopularDomainsUseCase
) : ViewModel() {

    private val handler: GeneralEventsHandler = GeneralEventsHandlerProvider.generalEventsHandler

    private val _recentSearches = MutableLiveData<Resource<List<DomainHistoryUIModel>>>()
    val recentSearches: LiveData<Resource<List<DomainHistoryUIModel>>> = _recentSearches

    private val _popularDomains = MutableLiveData<Resource<List<DomainInformationUIModel>>>()
    val popularDomains: LiveData<Resource<List<DomainInformationUIModel>>> = _popularDomains

    init {
        viewModelScope.launchWithLoadingOverlay(handler) {
            _recentSearches.value = recentSearchesHomeUseCase.execute()
            _popularDomains.value = getPopularDomainsUseCase.execute()
        }
    }
}
