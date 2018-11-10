package com.omstu.biznessapp.router

import com.omstu.biznessapp.ui.auth.LoginActivity
import com.omstu.biznessapp.ui.groups.GroupsActivity
import com.omstu.biznessapp.ui.main.MainActivity
import com.omstu.biznessapp.ui.table.TableActivity
import com.omstu.biznessapp.ui.sign.SignActivity

enum class Screen(val clazz: Class<*>) {
    MAIN(MainActivity::class.java),
    LOGIN(LoginActivity::class.java),
    GROUPS(GroupsActivity::class.java),
    TABLE(TableActivity::class.java),
    SIGN(SignActivity::class.java),
}