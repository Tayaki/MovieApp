package hu.bme.aut.movieapp.ui.create

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import hu.bme.aut.movieapp.R
import hu.bme.aut.movieapp.injector
import hu.bme.aut.movieapp.model.Show
import kotlinx.android.synthetic.main.fragment_create.*
import javax.inject.Inject

class CreateFragment : DialogFragment(), CreateScreen {
    @Inject
    lateinit var createPresenter: CreatePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_save.setOnClickListener {
            val show = Show()
            createPresenter.addShow(show)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
        createPresenter.attachScreen(this)
    }

    override fun onDetach() {
        createPresenter.detachScreen()
        super.onDetach()
    }

    override fun closeDialog() {
        dismiss()
    }
}