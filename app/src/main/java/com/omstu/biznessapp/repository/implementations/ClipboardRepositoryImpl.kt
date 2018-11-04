package com.mney.wallet.repository.implementations

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import com.mney.wallet.repository.ClipboardRepository


class ClipboardRepositoryImpl(val context: Context) : ClipboardRepository {
    override fun copy(text: String) {
        val clipboardManager = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("Source Text", text)
        clipboardManager.primaryClip = clipData
    }

}
