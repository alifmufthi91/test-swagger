package com.example.testswagger

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginFragment : Fragment() {
    private val mRegisterFragment = RegisterFragment.newInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(
            requireActivity().viewModelStore,
            ViewModelProvider.NewInstanceFactory()
        )[LoginViewModel::class.java]

        btn_login.setOnClickListener {
            val username = et_username_login.text.toString()
            val password = et_password_login.text.toString()
            val call = ApiMain().client.login(LoginData(username, password))
            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    val result = response.body()
                    Log.d("result :", response.message())
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
        btn_goto_register.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(
                    R.id.frame_container,
                    mRegisterFragment,
                    RegisterFragment::class.java.simpleName
                )
                ?.commit()
        }
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            LoginFragment()
    }
}