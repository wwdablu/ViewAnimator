package com.soumya.wwdablu.viewanimator.animators

import android.view.View
import com.soumya.wwdablu.viewanimator.ViewAnimator
import com.soumya.wwdablu.viewanimator.animations.Move
import com.soumya.wwdablu.viewanimator.animations.Property
import kotlinx.coroutines.*

abstract class BaseViewAnimator<T: ViewAnimator, V: View> {

    interface LoopCallback {
        fun done()
    }

    abstract fun getView() : V
    abstract fun getViewAnimator() : T

    private var next: ((T) -> Unit)? = null

    fun then(lambda: ((T) -> Unit)) {
        next = lambda
    }

    fun waitAndThen(duration: Long, lambda: (T) -> Unit) {

        CoroutineScope(Dispatchers.IO).launch {
            delay(duration)

            withContext(Dispatchers.Main) {
                lambda(getViewAnimator())
            }
        }
    }

    fun loop(logic: (viewAnimator: T, callback: LoopCallback) -> Unit,
             condition: () -> Boolean,
             finally: (viewAnimator: T) -> Unit) {

        if(!condition()) {
            finally(getViewAnimator())
            return
        }

        logic(getViewAnimator(), object : LoopCallback {
            override fun done() {
                loop(logic, condition, finally)
            }
        })
    }

    fun move(pixels: Float, on: Axis, duration: Long = 500) : T {
        Move(getView()).to(pixels, on, duration, getDefaultViewAnimatorListener())
        return getViewAnimator()
    }

    fun slide(type: Action, direction: Direction, duration: Long = 500) : T {
        Move(getView()).slide(type, direction, duration, getDefaultViewAnimatorListener())
        return getViewAnimator()
    }

    fun fade(type: Action, duration: Long = 500) : T {
        Property(getView()).fade(type, duration, getDefaultViewAnimatorListener())
        return getViewAnimator()
    }

    fun scale(type: Action, duration: Long = 500) : T {
        Property(getView()).scale(type, duration, getDefaultViewAnimatorListener())
        return getViewAnimator()
    }

    inline fun <reified S> switch(viewAnimator: S) : S {
        return viewAnimator
    }

    protected fun getDefaultViewAnimatorListener() : ViewAnimatorListener {
        return object : ViewAnimatorListener() {
            override fun onAnimationEnd() {
                executeNext()
            }
        }
    }

    protected fun executeNext() {
        val nextCall = next
        next = null
        nextCall?.invoke(getViewAnimator())
    }
}