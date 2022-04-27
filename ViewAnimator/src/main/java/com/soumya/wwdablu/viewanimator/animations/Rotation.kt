package com.soumya.wwdablu.viewanimator.animations

import android.animation.Animator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.soumya.wwdablu.viewanimator.animators.Axis
import com.soumya.wwdablu.viewanimator.animators.Rotate
import com.soumya.wwdablu.viewanimator.animators.ViewAnimatorListener

internal class Rotation internal constructor(private val view: View) : BaseAnimation() {

    fun by(degree: Float, rotation: Rotate, duration: Long = 500, listener: ViewAnimatorListener? = null) {

        view.animate().apply {
            this.duration = duration
            interpolator = AccelerateDecelerateInterpolator()
            setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(p0: Animator?) {
                    listener?.onAnimationStart()
                }

                override fun onAnimationEnd(p0: Animator?) {
                    listener?.onAnimationEnd()
                }

                override fun onAnimationCancel(p0: Animator?) {
                    listener?.onAnimationEnd()
                }

                override fun onAnimationRepeat(p0: Animator?) {
                    //
                }
            })

            rotationBy(if(rotation == Rotate.Clockwise) {
                degree
            } else {
                -degree
            })
        }
    }

    fun flip(axis: Axis, degree: Float, duration: Long = 500, listener: ViewAnimatorListener? = null) {

        view.animate().apply {
            this.duration = duration
            setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(p0: Animator?) {
                    listener?.onAnimationStart()
                }

                override fun onAnimationEnd(p0: Animator?) {
                    listener?.onAnimationEnd()
                }

                override fun onAnimationCancel(p0: Animator?) {
                    listener?.onAnimationEnd()
                }

                override fun onAnimationRepeat(p0: Animator?) {
                    //
                }
            })

            if(axis == Axis.X) {
                rotationXBy(degree)
            } else {
                rotationYBy(degree)
            }
        }
    }
}