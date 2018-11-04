package com.mney.wallet.router

import com.mney.wallet.ui.auth.CodeConfirmActivity
import com.mney.wallet.ui.auth.LoginActivity
import com.mney.wallet.ui.auth.RegisterActivity
import com.mney.wallet.ui.auth.WelcomeActivity
import com.mney.wallet.ui.currencies.CurrencyListActivity
import com.mney.wallet.ui.main.MainActivity
import com.mney.wallet.ui.qr.QrScanActivity
import com.mney.wallet.ui.settings.SettingsActivity
import com.mney.wallet.ui.transactions.TransactionsActivity

enum class Screen(val clazz: Class<*>) {
    MAIN(MainActivity::class.java),
    WELCOME(WelcomeActivity::class.java),
    REGISTER(RegisterActivity::class.java),
    LOGIN(LoginActivity::class.java),
    TRANSACTIONS(TransactionsActivity::class.java),
    SETTINGS(SettingsActivity::class.java),
    CODE_CONFIRM(CodeConfirmActivity::class.java),
    QR(QrScanActivity::class.java),
    CURRENCIES_LIST(CurrencyListActivity::class.java);
}