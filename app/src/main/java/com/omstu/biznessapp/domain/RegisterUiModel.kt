package com.mney.wallet.domain

data class RegisterUiModel(
        val firstName: String,
        val lastName: String,
        val phone: String,
        val eMail: String,
        val password: String = "",
        val passwordConfirmation: String = ""
)