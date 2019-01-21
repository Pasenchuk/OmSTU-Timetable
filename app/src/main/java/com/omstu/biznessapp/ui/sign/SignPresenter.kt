package com.omstu.biznessapp.ui.sign

import android.graphics.Color
import com.google.gson.Gson
import com.kasib.stlsdk.sign.SignView
import com.omstu.biznessapp.R
import com.omstu.biznessapp.di.AppModule
import com.omstu.biznessapp.repository.LocalRepository
import com.omstu.biznessapp.router.RouterCommand
import com.omstu.biznessapp.ui.base.BasePresenter
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileOutputStream


class SignPresenter(appModule: AppModule) : BasePresenter(appModule) {

    fun onInit(signView: SignView) {
        signView.setColor(localRepository.color)
        signView.setStrokeWidth(localRepository.strokeWidth.toFloat())
    }

    fun onFabClick(signView: SignView) {
        progressVisibility.postValue(true)

        postRouterCommandQueue(RouterCommand.ShowToastRes(R.string.sending), RouterCommand.Close())
        val fileName = saveSignatureFile(signView)

        val signImageFile = File(fileName)

        val signImagePart = MultipartBody.Part.createFormData("signImage", signImageFile.getName(), RequestBody.create(MediaType.parse("image/png"), signImageFile));
        val signJsonPart = MultipartBody.Part.createFormData("signJson", Gson().toJson(signView.sign));
        val tableJsonPart = MultipartBody.Part.createFormData("tableJson", Gson().toJson(localRepository.changedStudentsData));

        val subscribe = networkRepository
                .updateTable(tableJsonPart, signJsonPart, signImagePart)
                .compose(appModule.schedulersRepository.networkAsyncTransformer())
                .subscribe({
                    postRouterCommandQueue(RouterCommand.ShowToastRes(R.string.sending), RouterCommand.Close())
                }, errorHandler)
    }

    fun onOptionsItemSelected(itemId: Int, signView: SignView) {
        if (itemId == R.id.action_clear) {
            signView.clear()

        } else if (itemId == R.id.action_finish) {
            if (signView.sign.isEmpty())
                postRouterCommandQueue(RouterCommand.ShowToastRes(R.string.no_signature_detected))
            else {

            }

        } else if (itemId == R.id.action_black) {
            setColor(Color.BLACK, signView)

        } else if (itemId == R.id.action_blue) {
            setColor(Color.BLUE, signView)

        } else if (itemId == R.id.action_red) {
            setColor(Color.RED, signView)

        } else if (itemId == R.id.action_green) {
            setColor(Color.GREEN, signView)

        } else if (itemId == R.id.action_stroke) {
            postRouterCommandQueue(RouterCommand.ShowValueDialog(R.string.set_line_width, localRepository.strokeWidth, LocalRepository.MAX_WIDTH) {
                localRepository.strokeWidth = it
                signView.setStrokeWidth(it.toFloat())
            })

        } else if (itemId == R.id.action_save) {
            try {
                val fileName = saveSignatureFile(signView)
                postRouterCommandQueue(RouterCommand.ShowToastStr("${appModule.resourceRepository(R.string.image_saved_at)} $fileName"))
            } catch (ignored: Exception) {
            }


        } else if (itemId == android.R.id.home) {
            postRouterCommandQueue(RouterCommand.Close())

        }
    }

    private fun saveSignatureFile(signView: SignView): String {
        val fileName = "${appModule.localPathRepository()}/sign-${appModule.dateRepository()}.png"
        signView.saveFile(FileOutputStream(fileName))
        return fileName
    }

    private fun setColor(color: Int, signView: SignView) {
        signView.setColor(color)
        localRepository.color = color
    }
}