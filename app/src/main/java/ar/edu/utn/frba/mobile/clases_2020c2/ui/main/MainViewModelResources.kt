package ar.edu.utn.frba.mobile.clases_2020c2.ui.main

import android.content.Context
import ar.edu.utn.frba.mobile.clases_2020c2.R

class MainViewModelResources(val context: Context): MainViewModel.ResourceProvider {

    override val salute: String
        get() = context.getString(R.string.salute)
}