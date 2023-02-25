package com.vk.directop.lifecyclealeks.fragments.contract

import android.graphics.BitmapFactory
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner

typealias ResultListener<T> = (T) -> Unit

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}
interface Navigator {

    //fun showBoxSelectionScreen(options: Options)

    fun showOptionsScreen(options: BitmapFactory.Options)
    fun showAboutScreen()

    fun showCongratulationsScreen()

    fun goBack()

    fun goToMenu()

    fun <T : Parcelable> publishResult(result: T)

    fun <T : Parcelable> listenResult(clazz: Class<T>, owner: LifecycleOwner, listener: ResultListener<T>)
}