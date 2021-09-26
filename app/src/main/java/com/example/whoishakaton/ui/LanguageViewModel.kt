package com.example.whoishakaton.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoishakaton.data.local.db.entities.LanguageEntity
import com.example.whoishakaton.domain.use_cases.local.AddLanguageUseCase
import com.example.whoishakaton.domain.use_cases.local.GetLanguageByIdUseCase
import com.example.whoishakaton.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(
    private val addLanguageUseCase: AddLanguageUseCase,
    private val getLanguageByIdUseCase: GetLanguageByIdUseCase
) : ViewModel() {

    private var nextLang = ""

    private val _context = MutableLiveData<Context>()
    val context: LiveData<Context> = _context

    private val _language = MutableLiveData<String>()
    val language: LiveData<String> = _language

    init {
        getLanguage()
    }

    fun setContext(context: Context) {
        _context.value = context
    }

    fun getLanguage() = viewModelScope.launch {
        val result = getLanguageByIdUseCase.execute(1)

        if (result is Resource.Success) {
            if (result.data == null) {
                _language.value = "en"
                nextLang = LANG_SR
                addLanguageUseCase.execute(LanguageEntity(language = "en"))
            } else {
                nextLang = if (result.data.language == LANG_EN) LANG_SR else LANG_EN
                _language.value = result.data.language
            }
        } else if (result is Resource.Failure) {
            println(result)
        }
    }

    fun toggleLanguage() = viewModelScope.launch {
        val result = addLanguageUseCase.execute(LanguageEntity(language = nextLang))

        if (result is Resource.Success) {
            _language.value = nextLang
            nextLang = if (nextLang == LANG_EN) LANG_SR else LANG_EN
        } else if (result is Resource.Failure) {
            println(result)
        }
    }
}

const val LANG_EN = "en"
const val LANG_SR = "sr"
