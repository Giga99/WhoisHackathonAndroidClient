package com.example.whoishakaton.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoishakaton.data.remote.responses.SearchDomainRequest
import com.example.whoishakaton.domain.models.DomainHistoryUIModel
import com.example.whoishakaton.domain.models.DomainInformationUIModel
import com.example.whoishakaton.domain.use_cases.local.AddNewSearchUseCase
import com.example.whoishakaton.domain.use_cases.remote.SearchDomainUseCase
import com.example.whoishakaton.utils.Resource
import com.example.whoishakaton.utils.overlays_and_dialogs.GeneralEventsHandler
import com.example.whoishakaton.utils.overlays_and_dialogs.GeneralEventsHandlerProvider
import com.example.whoishakaton.utils.overlays_and_dialogs.launchWithLoadingOverlay
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val addNewSearchUseCase: AddNewSearchUseCase,
    private val searchDomainUseCase: SearchDomainUseCase
) : ViewModel() {

    private val handler: GeneralEventsHandler = GeneralEventsHandlerProvider.generalEventsHandler

    private val _searchDomain = MutableLiveData<Resource<DomainInformationUIModel>>()
    val searchDomain: LiveData<Resource<DomainInformationUIModel>> = _searchDomain

    fun search(domainTitle: String) = viewModelScope.launchWithLoadingOverlay(handler) {
        val time = System.currentTimeMillis()
        addNewSearchUseCase.execute(DomainHistoryUIModel(domainTitle, time))

        val searchResult = searchDomainUseCase.execute(SearchDomainRequest(domainTitle))

        if (searchResult is Resource.Success) {
            _searchDomain.value = Resource.Success(searchResult.data)
        } else if (searchResult is Resource.Failure) {
            _searchDomain.value = Resource.Failure(searchResult.throwable)
        }
    }
}
