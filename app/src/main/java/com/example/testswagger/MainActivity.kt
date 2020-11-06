package com.example.testswagger

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testswagger.login.LoginFragment
import com.example.testswagger.register.RegisterFragment


class MainActivity : AppCompatActivity() {
    private val mFragmentManager = supportFragmentManager
    private val mLoginFragment = LoginFragment.newInstance()
    private var fragment =
        mFragmentManager.findFragmentByTag(LoginFragment::class.java.simpleName)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (fragment !is RegisterFragment) {
            mFragmentManager
                .beginTransaction()
                .add(
                    R.id.frame_container,
                    mLoginFragment,
                    LoginFragment::class.java.simpleName
                )
                .commit()
        }
    }

}