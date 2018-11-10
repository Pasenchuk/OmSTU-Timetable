package com.omstu.biznessapp.ui.sign

import android.os.Bundle
import com.omstu.biznessapp.R
import com.omstu.biznessapp.di.AppModule
import com.omstu.biznessapp.ui.base.BaseActivity
import com.omstu.biznessapp.ui.base.BasePresenter
import kotlinx.android.synthetic.main.activity_sign.*

class SignActivity : BaseActivity() {

    private lateinit var signPresenter: SignPresenter

    override fun initPresenter(appModule: AppModule): BasePresenter = SignPresenter(appModule)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        signPresenter = getPresenter(SignPresenter::class.java)

        R.id.v_sign_fab calls { signPresenter.onFabClick(v_sign.signInJSON)}
    }
}
