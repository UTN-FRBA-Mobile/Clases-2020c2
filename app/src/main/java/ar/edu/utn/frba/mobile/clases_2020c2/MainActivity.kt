package ar.edu.utn.frba.mobile.clases_2020c2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ar.edu.utn.frba.mobile.clases_2020c2.ui.main.MainFragment
import ar.edu.utn.frba.mobile.clases_2020c2.ui.main.MainViewModel
import ar.edu.utn.frba.mobile.clases_2020c2.ui.main.MainViewModelResources
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(toolbar)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }
}