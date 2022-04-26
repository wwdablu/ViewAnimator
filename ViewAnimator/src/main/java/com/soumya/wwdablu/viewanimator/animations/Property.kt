package com.soumya.wwdablu.viewanimator.animations

import android.animation.Animator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.soumya.wwdablu.viewanimator.animators.Action
import com.soumya.wwdablu.viewanimator.animators.ViewAnimatorListener

internal class Property internal constructor(private val view: View) : BaseAnimation() {

    fun fade(type: Action,
             duration: Long = 500,
             listener: ViewAnimatorListener? = null) {

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

            alpha(if(type == Action.In) 1F else 0F)
        }
    }

    fun scale(type: Action, duration: Long = 500, listener: ViewAnimatorListener? = null) {

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

            scaleX(if(type == Action.In) 1F else 0F)
            scaleY(if(type == Action.In) 1F else 0F)
        }
    }
}