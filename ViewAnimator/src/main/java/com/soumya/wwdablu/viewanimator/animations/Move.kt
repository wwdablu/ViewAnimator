package com.soumya.wwdablu.viewanimator.animations

import android.animation.Animator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.soumya.wwdablu.viewanimator.animators.Axis
import com.soumya.wwdablu.viewanimator.animators.Direction
import com.soumya.wwdablu.viewanimator.animators.Action
import com.soumya.wwdablu.viewanimator.animators.ViewAnimatorListener

internal class Move internal constructor(private val view: View) : BaseAnimation() {

    fun to(to: Float, axis: Axis, duration: Long = 500, listener: ViewAnimatorListener? = null) {
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

            when (axis) {
                Axis.X -> this.translationX(to)
                Axis.Y -> this.translationY(to)
            }
        }
    }

    fun slide(action: Action,
              direction: Direction,
              duration: Long = 500, listener:
              ViewAnimatorListener? = null) {

        when (direction) {
            Direction.Top -> {
                if(action == Action.In) {
                    view.y = view.y - (view.y + view.height)
                    to(0F, Axis.Y, duration, listener)
                } else {
                    to(-(view.height + getTopMargin(view)), Axis.Y, duration, listener)
                }
            }

            Direction.Left -> {
                if(action == Action.In) {
                    view.x = view.x - (view.x + view.width)
                    to(0F, Axis.X, duration, listener)
                } else {
                    to(-(view.width + getLeftMargin(view)), Axis.X, duration, listener)
                }
            }

            Direction.Right -> {
                if(action == Action.In) {
                    view.x = view.x + (getRightMargin(view) + view.width)
                    to(0F, Axis.X, duration, listener)
                } else {
                    to((view.width + getRightMargin(view)), Axis.X, duration, listener)
                }
            }

            Direction.Bottom -> {
                if(action == Action.In) {
                    view.y = view.y + (getBottomMargin(view) + view.height)
                    to(0F, Axis.Y, duration, listener)
                } else {
                    to((view.height + getBottomMargin(view)), Axis.Y, duration, listener)
                }
            }
        }
    }
}