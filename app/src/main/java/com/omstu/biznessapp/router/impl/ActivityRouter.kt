package com.mney.wallet.router.impl

import android.arch.lifecycle.MutableLiveData
import android.content.Intent
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.mney.wallet.router.Router
import com.mney.wallet.router.RouterCommand

class ActivityRouter(private val activity: AppCompatActivity, liveData: MutableLiveData<MutableList<RouterCommand>>) : Router(activity, liveData) {

    override fun executeCommand(command: RouterCommand) {
        when (command) {
            is RouterCommand.OpenScreen -> activity.startActivity(Intent(activity, command.screen.clazz).addFlags(command.flag))
            is RouterCommand.ShowTextError -> activity.findViewById<View>(command.vId).let { if (it is TextView) it.error = activity.getString(command.resId) }
            is RouterCommand.HideTextError -> activity.findViewById<View>(command.vId).let { if (it is TextView) it.error = null }
            is RouterCommand.ShowToastRes -> Toast.makeText(activity, command.resId, Toast.LENGTH_SHORT).show()
            is RouterCommand.ShowToastStr -> Toast.makeText(activity, command.message, Toast.LENGTH_SHORT).show()
            is RouterCommand.ShowSnakeBarRes ->
                Snackbar.make((activity.findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0), command.resId, Snackbar.LENGTH_LONG)
                        .setAction(command.actionId, { command.callback() }).show()
            is RouterCommand.Close -> activity.finish()
            is RouterCommand.OpenForResult -> activity.startActivityForResult(Intent(activity, command.screen.clazz), command.requestCode)
            is RouterCommand.SetResult -> TODO()
            is RouterCommand.ShowDialog -> {
                val builder = AlertDialog
                        .Builder(activity)
                        .setTitle(command.title)
                        .setMessage(command.message)
                command.apply {
                    when {
                        positiveButton == null && neutralButton == null ->
                            builder.setPositiveButton(android.R.string.ok, { _, _ -> })
                        else ->
                            builder.setNegativeButton(android.R.string.cancel, { _, _ -> })
                    }
                    positiveButton?.apply {
                        builder.setPositiveButton(title, { _, _ -> cb() })
                    }
                    neutralButton?.apply {
                        builder.setNeutralButton(title, { _, _ -> cb() })
                    }
                }
                builder.show();
            }
        }
    }
}
