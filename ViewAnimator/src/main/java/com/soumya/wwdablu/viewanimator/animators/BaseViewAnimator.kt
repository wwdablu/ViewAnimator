package com.soumya.wwdablu.viewanimator.animators

import android.view.View
import com.soumya.wwdablu.viewanimator.ViewAnimator
import com.soumya.wwdablu.viewanimator.animations.Move
import com.soumya.wwdablu.viewanimator.animations.Property
import com.soumya.wwdablu.viewanimator.animations.Rotation
import kotlinx.coroutines.*

abstract class BaseViewAnimator<T: ViewAnimator, V: View> {

    interface LoopCallback {
        fun done()
    }

    abstract fun getView() : V
    abstract fun getViewAnimator() : T

    private var next: ((T) -> Unit)? = null
    private var waitTime: Long = 0L

    fun then(waitFor: Long = 0, nextCall: ((T) -> Unit)) {
        waitTime = waitFor
        next = nextCall
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

    fun scale(to: Float, duration: Long = 500) : T {
        Property(getView()).scale(to, duration, getDefaultViewAnimatorListener())
        return getViewAnimator()
    }

    fun rotate(degree: Float, by: Rotate, duration: Long = 500) : T {
        Rotation(getView()).by(degree, by, duration,getDefaultViewAnimatorListener())
        return getViewAnimator()
    }

    fun flip(axis: Axis, degree: Float, duration: Long = 500) : T {
        Rotation(getView()).flip(axis, degree, duration, getDefaultViewAnimatorListener())
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

        if(waitTime == 0L) {
            nextCall?.invoke(getViewAnimator())
        } else {
            CoroutineScope(Dispatchers.IO).launch {
                delay(waitTime)
                waitTime = 0L

                withContext(Dispatchers.Main) {
                    nextCall?.invoke(getViewAnimator())
                }
            }
        }
    }
}