package com.example.ui.details

import com.example.data.networking.response.similarmovies.Result

interface ContractDetailsMovie {

    interface View {
        fun initView()
        fun showLoading(result: Result)
    }
}