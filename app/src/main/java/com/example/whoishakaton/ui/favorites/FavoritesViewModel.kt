package com.example.whoishakaton.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoishakaton.data.remote.responses.SearchDomainRequest
import com.example.whoishakaton.domain.models.DomainInformationUIModel
import com.example.whoishakaton.domain.models.equal
import com.example.whoishakaton.domain.use_cases.local.AddDomainToFavoritesUseCase
import com.example.whoishakaton.domain.use_cases.local.GetFavoriteDomainsUseCase
import com.example.whoishakaton.domain.use_cases.remote.SearchDomainUseCase
import com.example.whoishakaton.utils.Resource
import com.example.whoishakaton.utils.overlays_and_dialogs.GeneralEventsHandler
import com.example.whoishakaton.utils.overlays_and_dialogs.GeneralEventsHandlerProvider
import com.example.whoishakaton.utils.overlays_and_dialogs.launchWithLoadingOverlay
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoriteDomainsUseCase: GetFavoriteDomainsUseCase,
    private val searchDomainUseCase: SearchDomainUseCase,
    private val addDomainToFavoritesUseCase: AddDomainToFavoritesUseCase
) : ViewModel() {

    private val handler: GeneralEventsHandler = GeneralEventsHandlerProvider.generalEventsHandler

    private lateinit var favorites: List<DomainInformationUIModel>
    private val _favoriteDomains = MutableLiveData<Resource<List<DomainInformationUIModel>>>()
    val favoriteDomains: LiveData<Resource<List<DomainInformationUIModel>>> = _favoriteDomains

    init {
        viewModelScope.launchWithLoadingOverlay(handler) {
            val result = getFavoriteDomainsUseCase.execute()

            if (result is Resource.Success) {
                favorites = result.data
                _favoriteDomains.value = Resource.Success(result.data)
            } else if (result is Resource.Failure) {
                _favoriteDomains.value = result
            }
        }
    }

    fun updateDomains() = viewModelScope.launchWithLoadingOverlay(handler) {
        val domains = mutableListOf<DomainInformationUIModel>()

        favorites.forEach {
            val domainResult = searchDomainUseCase.execute(SearchDomainRequest(it.name))

            if (domainResult is Resource.Success) {
                if (!domainResult.data.equal(it))
                    addDomainToFavoritesUseCase.execute(domainResult.data)

                domains.add(domainResult.data)
            } else if (domainResult is Resource.Failure) {
                _favoriteDomains.value = Resource.Failure(domainResult.throwable)
            }
        }

        favorites = domains
        _favoriteDomains.value = Resource.Success(domains)
    }
}

