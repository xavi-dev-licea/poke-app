package com.xavidev.pokeapp.utils

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.ViewModel

open class ObservableViewModel : ViewModel(), Observable {

    private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    fun notifyChange() {
        callbacks.notifyChange(this, 0)
    }

    fun notifyPropertyChanged(propertyId: Int) {
        callbacks.notifyChange(this, propertyId)
    }
}