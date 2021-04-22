package com.example.ui.login

import com.example.movieslist_mvp.ui.login.MainContract

class MainPresenter(private val view: MainContract.View): MainContract.Presenter {
    override fun login(userName: String, password: String) {
        when {
            userName.isEmpty() -> {
                view.showErroToast("Preencha o seu nome")

            }
            password.isEmpty() -> {
                view.showErroToast("Preencha a sua senha")
            }
            else -> {
                view.goToMoviesScreen()
            }
        }
    }
}