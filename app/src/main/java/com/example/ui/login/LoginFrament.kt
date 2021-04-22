package com.example.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.movieslist_mvp.ui.login.MainContract
import com.example.ui.R
import kotlinx.android.synthetic.main.fragment_login_frament.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFrament.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFrament : Fragment(), MainContract.View {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var presenter: MainPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

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
        findNavController().navigate(R.id.action_loginFrament_to_moviesListFragment)
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