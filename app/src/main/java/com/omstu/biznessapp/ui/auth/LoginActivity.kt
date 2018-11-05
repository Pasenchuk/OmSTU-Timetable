package com.omstu.biznessapp.ui.auth

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.widget.EditText
import com.omstu.biznessapp.ui.base.BaseActivity
import com.omstu.biznessapp.ui.base.BasePresenter
import com.omstu.biznessapp.R
import com.omstu.biznessapp.di.AppModule


class LoginActivity : BaseActivity() {

    private lateinit var loginPresenter: LoginPresenter

    private lateinit var vSignInEmail: EditText
    private lateinit var vSignInPassword: EditText

    override fun initPresenter(appModule: AppModule): BasePresenter = LoginPresenter(appModule)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginPresenter = getPresenter(LoginPresenter::class.java)

        initViews()

        loginPresenter.emailData.observe(this, Observer { vSignInEmail.setText(it) })
    }


    private fun initViews() {
        vSignInEmail = findViewById(R.id.v_sign_in_email)
        vSignInPassword = findViewById(R.id.v_sign_in_password)
        R.id.v_sign_in_btn_login calls { loginPresenter.onSignInClick(vSignInEmail.text.toString(), vSignInPassword.text.toString()) }
    }

}