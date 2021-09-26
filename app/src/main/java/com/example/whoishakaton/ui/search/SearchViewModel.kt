package com.example.whoishakaton.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoishakaton.data.remote.responses.SearchDomainRequest
import com.example.whoishakaton.domain.models.DomainHistoryUIModel
import com.example.whoishakaton.domain.models.DomainInformationUIModel
import com.example.whoishakaton.domain.use_cases.local.AddDomainToFavoritesUseCase
import com.example.whoishakaton.domain.use_cases.local.AddFavoriteDomainUseCase
import com.example.whoishakaton.domain.use_cases.local.AddNewSearchUseCase
import com.example.whoishakaton.domain.use_cases.local.GetDomainByTitleUseCase
import com.example.whoishakaton.domain.use_cases.remote.SearchDomainUseCase
import com.example.whoishakaton.ui.search.SearchViewModel.AddRemoveFavoriteResult.FailedResult
import com.example.whoishakaton.ui.search.SearchViewModel.AddRemoveFavoriteResult.SuccessfulResult
import com.example.whoishakaton.utils.OneTimeEvent
import com.example.whoishakaton.utils.Resource
import com.example.whoishakaton.utils.overlays_and_dialogs.GeneralEventsHandler
import com.example.whoishakaton.utils.overlays_and_dialogs.GeneralEventsHandlerProvider
import com.example.whoishakaton.utils.overlays_and_dialogs.launchWithLoadingOverlay
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val addNewSearchUseCase: AddNewSearchUseCase,
    private val searchDomainUseCase: SearchDomainUseCase,
    private val addDomainToFavoritesUseCase: AddDomainToFavoritesUseCase,
    private val getDomainByTitleUseCase: GetDomainByTitleUseCase,
    private val addFavoriteDomainUseCase: AddFavoriteDomainUseCase
) : ViewModel() {

    private val handler: GeneralEventsHandler = GeneralEventsHandlerProvider.generalEventsHandler

    lateinit var domain: DomainInformationUIModel
    private val _searchDomain = MutableLiveData<Resource<DomainInformationUIModel>>()
    val searchDomain: LiveData<Resource<DomainInformationUIModel>> = _searchDomain

    private val _addRemoveFavorite = MutableLiveData<OneTimeEvent<AddRemoveFavoriteResult>>()
    val addRemoveFavorite: LiveData<OneTimeEvent<AddRemoveFavoriteResult>> = _addRemoveFavorite

    fun search(domainTitle: String) = viewModelScope.launchWithLoadingOverlay(handler) {
        val time = System.currentTimeMillis()
        addNewSearchUseCase.execute(DomainHistoryUIModel(domainTitle, time))

        val searchResult = searchDomainUseCase.execute(SearchDomainRequest(domainTitle))

        if (searchResult is Resource.Success) {
            domain = searchResult.data
            _searchDomain.value = Resource.Success(searchResult.data)
        } else if (searchResult is Resource.Failure) {
            _searchDomain.value = Resource.Failure(searchResult.throwable)
        }

        val favoriteResult = getDomainByTitleUseCase.execute(domainTitle)

        if (favoriteResult is Resource.Success) {
            _addRemoveFavorite.value = OneTimeEvent(SuccessfulResult(favoriteResult.data))
        } else if (favoriteResult is Resource.Failure) {
            _addRemoveFavorite.value = OneTimeEvent(FailedResult(favoriteResult.throwable))
        }
    }

    fun addRemoveFavorite() = viewModelScope.launchWithLoadingOverlay(handler) {
        val result = addDomainToFavoritesUseCase.execute(domain)

        if (result is Resource.Success) {
            _addRemoveFavorite.value = OneTimeEvent(SuccessfulResult(result.data))
        } else if (result is Resource.Failure) {
            _addRemoveFavorite.value = OneTimeEvent(FailedResult(result.throwable))
        }
    }

    fun addFavorite() = viewModelScope.launchWithLoadingOverlay(handler) {
        val result = addFavoriteDomainUseCase.execute(domain)

        if (result is Resource.Success) {
            _addRemoveFavorite.value = OneTimeEvent(SuccessfulResult(true))
        } else if (result is Resource.Failure) {
            _addRemoveFavorite.value = OneTimeEvent(FailedResult(result.throwable))
        }
    }

    sealed class AddRemoveFavoriteResult {

        data class SuccessfulResult(val add: Boolean) : AddRemoveFavoriteResult()

        data class FailedResult(val throwable: Throwable) : AddRemoveFavoriteResult()
    }
}
