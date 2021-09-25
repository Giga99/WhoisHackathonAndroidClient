/*
 * Copyright (C) 2021 LotusFlare
 * All Rights Reserved.
 * Unauthorized copying and distribution of this file, via any medium is strictly prohibited.
 */

package com.example.whoishakaton.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface EventHandler<out T> {

    fun handleEvent(action: (T) -> Unit)
}

/**
 * To be used in combination with LiveData (most commonly).
 * This makes it possible to handle one-time events while observing LiveData changes.
 */
data class OneTimeEvent<out T>(
    private var event: T?
) : EventHandler<T> {

    /**
     * Calls [action] to handle event [T], if it was not handled yet, otherwise does nothing.
     */
    override fun handleEvent(action: (T) -> Unit) = event?.let(action).also { event = null } ?: Unit

}

fun <T> LiveData<OneTimeEvent<T>>.oneTimeEventObserve(
    owner: LifecycleOwner,
    callback: (T) -> Unit
) {
    this.observe(owner) {
        it.handleEvent { callback(it) }
    }
}

fun <T> MutableLiveData<OneTimeEvent<T>>.postOneTimeEvent(event: T) {
    this.postValue(OneTimeEvent(event))
}

fun <T> MutableLiveData<OneTimeEvent<T>>.setOneTimeEvent(event: T) {
    this.value = OneTimeEvent(event)
}
