package com.example.testswagger.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.testswagger.ApiMain
import com.example.testswagger.R
import com.example.testswagger.ResponseBody
import kotlinx.android.synthetic.main.fragment_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class RegisterFragment : Fragment() {
    private val apiClient = ApiMain.getInstance().client

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_register.setOnClickListener {
            val username = et_username.text.toString()
            val password = et_password.text.toString()
            val email = et_email.text.toString()
            val call = apiClient.register(RegisterData(username, password, email))
            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    val result = response.body()
                    if (result != null){
                        tv_response.text = result.message.toString()
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(requireContext(), t.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegisterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            RegisterFragment()
    }
}