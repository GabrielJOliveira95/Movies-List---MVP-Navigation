package com.example.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.extensions.slideWithEffect
import com.example.movieslist_mvp.ui.login.MainContract
import com.example.ui.R
import kotlinx.android.synthetic.main.fragment_login_frament.*

class LoginFragment : Fragment(), MainContract.View {
    private lateinit var presenter: MainPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = MainPresenter(this)
        return inflater.inflate(R.layout.fragment_login_frament, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    override fun showErroToast(erro: String) {
        Toast.makeText(context, erro, Toast.LENGTH_LONG).show()
    }

    override fun goToMoviesScreen() {
        findNavController().slideWithEffect(R.id.action_loginFrament_to_moviesListFragment, null)
    }

    override fun setListeners() {
        view.let { root ->
            root.run {
                loginScreemLoginBtn.setOnClickListener {
                    val userName = loginScreemUserNameEt.text.toString()
                    val password = loginScreemPasswordEt.text.toString()
                    presenter.login(userName = userName, password = password)
                }
            }
        }
    }
}
