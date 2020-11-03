package com.example.testswagger

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject
import java.io.IOException


class MainActivity : AppCompatActivity() {
    private lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = OkHttpClient.Builder()
            .build()


        btn_register.setOnClickListener {
            val email = et_email.text.toString()
            val password = et_password.text.toString()
            val username = et_username.text.toString()
            val postBody = "{\"email\":\"$email\",\"password\":\"$password\",\"username\":\"$username\"}"
            val body= RequestBody.create("application/json".toMediaTypeOrNull(), postBody)

            val request: Request = Request.Builder()
                .url("http://18.139.50.74:8080/register")
                .post(body)
                .build()
            Log.d("register", "email $email pass $password usn $username")
            val call = client.newCall(request)
            call.enqueue(object: okhttp3.Callback{
                override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                    runOnUiThread {
                        tv_response.text = response.body?.string()
                    }
                }

                override fun onFailure(call: okhttp3.Call, e: IOException) {

                }
            })
        }

        btn_login.setOnClickListener {
            val password = et_password_login.text.toString()
            val username = et_username_login.text.toString()
            val postBody = "{\"password\":\"$password\",\"username\":\"$username\"}"
            val body= RequestBody.create("application/json".toMediaTypeOrNull(), postBody)

            val request: Request = Request.Builder()
                .url("http://18.139.50.74:8080/login")
                .post(body)
                .build()
            Log.d("login", "pass $password usn $username")
            val call = client.newCall(request)
            call.enqueue(object: okhttp3.Callback{
                override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                    val responseBody = response.body?.string()
                    val responseObject : JSONObject = JSONObject(responseBody)
                    val data = responseObject.getJSONObject("data")
                    token = data.getString("token")
                    Log.d("token: ", token)
                    runOnUiThread {
                        tv_response_login.text = responseBody
                        tv_token.text = token
                    }
                }

                override fun onFailure(call: okhttp3.Call, e: IOException) {

                }
            })
        }
    }
}