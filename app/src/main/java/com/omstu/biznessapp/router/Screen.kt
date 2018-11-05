package com.omstu.biznessapp.router

import com.omstu.biznessapp.ui.auth.LoginActivity
import com.omstu.biznessapp.ui.main.MainActivity

enum class Screen(val clazz: Class<*>) {
    MAIN(MainActivity::class.java),
    LOGIN(LoginActivity::class.java),
    GROUPS(LoginActivity::class.java),
    TABLE(LoginActivity::class.java),
}