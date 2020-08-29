package ar.edu.utn.frba.mobile.clases_2020c2.ui.main

import androidx.lifecycle.ViewModel

class MainViewModel() : ViewModel() {
    lateinit var resources: ResourceProvider

    val salute get() = resources.salute

    interface ResourceProvider {
        val salute: String
    }
}