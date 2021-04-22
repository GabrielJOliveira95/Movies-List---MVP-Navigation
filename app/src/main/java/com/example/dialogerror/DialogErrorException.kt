package com.example.dialogerror

import android.content.Context
import android.os.Bundle
import android.view.View
import com.example.ui.R
import com.example.ui.databinding.DialogErroBinding
import kotlinx.android.synthetic.main.dialog_erro.*


class DialogErrorException(context: Context, titleError: String, mensageErro: String, listener: View.OnClickListener): BaseDialog(context) {
    private var listener: View.OnClickListener? = listener
    private var titleError: String? = titleError
    private var mensageErro: String? = mensageErro
    lateinit var binding: DialogErroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogErroBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)

        setWidthToMatchWindowsSize()
        setView()
        setListener()

    }

    private fun setListener(){
        if (listener != null){
            binding.btnTentarNovamente.setOnClickListener {
                listener!!.onClick(it)
                dismiss()
            }
        }
    }
    private fun setView(){
        if (mensageErro.equals("Unable to resolve host \"api.themoviedb.org\": No address associated with hostname")){
            binding.mensagemErroTv.text = context.getString(R.string.sem_internet_verifique_sua_conex_o_e_tente_novamente)
        } else {
            binding.mensagemErroTv.text = mensageErro
        }
    }
}