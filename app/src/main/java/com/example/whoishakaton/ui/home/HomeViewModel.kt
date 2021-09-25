package com.example.whoishakaton.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoishakaton.domain.models.DomainHistoryUIModel
import com.example.whoishakaton.domain.models.DomainUIModel
import com.example.whoishakaton.domain.use_cases.AddNewSearchUseCase
import com.example.whoishakaton.domain.use_cases.RecentSearchesHomeUseCase
import com.example.whoishakaton.utils.Resource
import com.example.whoishakaton.utils.overlays_and_dialogs.GeneralEventsHandler
import com.example.whoishakaton.utils.overlays_and_dialogs.GeneralEventsHandlerProvider
import com.example.whoishakaton.utils.overlays_and_dialogs.launchWithLoadingOverlay
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val addNewSearchUseCase: AddNewSearchUseCase,
    private val recentSearchesHomeUseCase: RecentSearchesHomeUseCase
) : ViewModel() {

    private val handler: GeneralEventsHandler = GeneralEventsHandlerProvider.generalEventsHandler

    private val _searchDomain = MutableLiveData<Resource<DomainUIModel>>()
    val searchDomain: LiveData<Resource<DomainUIModel>> = _searchDomain

    private val _recentSearches = MutableLiveData<Resource<List<DomainHistoryUIModel>>>()
    val recentSearches: LiveData<Resource<List<DomainHistoryUIModel>>> = _recentSearches

    init {
        viewModelScope.launchWithLoadingOverlay(handler) {
            _recentSearches.value = recentSearchesHomeUseCase.execute()
        }
    }

    fun search(domainTitle: String) = viewModelScope.launchWithLoadingOverlay(handler) {
        delay(3000)
        _searchDomain.value = Resource.Loading()

        val time = System.currentTimeMillis()
        val result = addNewSearchUseCase.execute(DomainHistoryUIModel(domainTitle, time))

        if (result is Resource.Success) {
            _searchDomain.value = Resource.Success(DomainUIModel(domainTitle))
        } else if (result is Resource.Failure) {
            _searchDomain.value = Resource.Failure(result.throwable)
        }
    }
}
