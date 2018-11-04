package com.omstu.biznessapp.domain

data class RegisterUiModel(
        val firstName: String,
        val lastName: String,
        val phone: String,
        val eMail: String,
        val password: String = "",
        val passwordConfirmation: String = ""
)