package com.omstu.biznessapp.router.impl

import android.arch.lifecycle.MutableLiveData
import android.content.Intent
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.omstu.biznessapp.router.Router
import com.omstu.biznessapp.router.RouterCommand

class FragmentRouter(private val fragment: Fragment, liveData: MutableLiveData<MutableList<RouterCommand>>) : Router(fragment, liveData) {

    override fun executeCommand(command: RouterCommand) {
        when (command) {
            is RouterCommand.OpenScreen -> fragment.startActivity(Intent(fragment.activity, command.screen.clazz).addFlags(command.flag))
            is RouterCommand.ShowTextError -> fragment.view?.findViewById<View>(command.vId).let { if (it is TextView) it.error = fragment.getString(command.resId) }
            is RouterCommand.HideTextError -> fragment.view?.findViewById<View>(command.vId).let { if (it is TextView) it.error = null }
            is RouterCommand.ShowToastRes -> Toast.makeText(fragment.activity, command.resId, Toast.LENGTH_SHORT).show()
            is RouterCommand.ShowToastStr -> Toast.makeText(fragment.activity, command.message, Toast.LENGTH_SHORT).show()
            is RouterCommand.Close -> fragment.activity?.finish()
            is RouterCommand.OpenForResult -> TODO()
            is RouterCommand.SetResult -> TODO()
            is RouterCommand.ShowSnakeBarRes -> fragment.view?.let {
                Snackbar.make(it, command.resId, Snackbar.LENGTH_LONG)
                        .setAction(command.actionId, { command.callback() }).show()
            }
            is RouterCommand.ShowDialog -> TODO()
        }
    }
}
