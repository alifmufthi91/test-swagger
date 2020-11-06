package com.example.testswagger.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.testswagger.*
import com.example.testswagger.register.RegisterFragment
import kotlinx.android.synthetic.main.fragment_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginFragment : Fragment() {
    private val mRegisterFragment = RegisterFragment.newInstance()
    private val apiMain = ApiMain.getInstance()
    private val apiClient = apiMain.client

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_login.setOnClickListener {
            val username = et_username_login.text.toString()
            val password = et_password_login.text.toString()
            val call = apiClient.login(LoginData(username, password))
            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if(response.code() == 200) {
                        val result = response.body()
                        if (result != null) {
                            if (result.statusCode == 2110) {
                                apiMain.token = result.data?.token ?: ""
                                tv_token.text = result.data?.token
                                val intent =
                                    Intent(requireActivity(), ChecklistActivity::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }
                    else{
                        Toast.makeText(requireContext(), "Authentikasi gagal", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(requireContext(), t.localizedMessage, Toast.LENGTH_SHORT).show()
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
                ?.addToBackStack("goRegister")
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