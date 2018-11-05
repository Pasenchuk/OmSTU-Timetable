package com.omstu.biznessapp.router

import android.content.Intent

sealed class RouterCommand {
    class OpenScreen(val screen: Screen, val flag: Int = Intent.FLAG_ACTIVITY_NEW_TASK) : RouterCommand()
    class OpenForResult(val screen: Screen, val requestCode: Int) : RouterCommand()
    class SetResult(val resultCode: Int) : RouterCommand()
    class ShowToastRes(val resId: Int) : RouterCommand()
    class ShowSnakeBarRes(val resId: Int, val actionId: Int, val callback: () -> Unit) : RouterCommand()
    class ShowTextError(val vId: Int, val resId: Int) : RouterCommand()
    class HideTextError(val vId: Int) : RouterCommand()
    class ShowToastStr(val message: String) : RouterCommand()
    class ShowDialog(val title: Int, val message: Int, val positiveButton: Button? = null, val neutralButton: Button? = null) : RouterCommand()
    class Close() : RouterCommand()
}