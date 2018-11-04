package com.omstu.biznessapp.router

import com.mney.wallet.ui.auth.LoginActivity
import com.omstu.biznessapp.MainActivity

enum class Screen(val clazz: Class<*>) {
    MAIN(MainActivity::class.java),
    LOGIN(LoginActivity::class.java),
}