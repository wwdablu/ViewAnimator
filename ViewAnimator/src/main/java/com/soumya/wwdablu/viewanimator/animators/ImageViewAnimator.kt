package com.soumya.wwdablu.viewanimator.animators

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.soumya.wwdablu.viewanimator.ViewAnimator
import com.soumya.wwdablu.viewanimator.animations.Rotation

class ImageViewAnimator constructor(private val imageView: ImageView) : BaseViewAnimator<ImageViewAnimator, ImageView>(), ViewAnimator {

    override fun getView(): ImageView {
        return imageView
    }

    override fun getViewAnimator(): ImageViewAnimator {
        return this
    }

    fun flipAndSetImage(axis: Axis, bitmap: Bitmap, duration: Long = 500) : ImageViewAnimator {

        Rotation(imageView).apply {
            flip(axis, 90F, duration, object : ViewAnimatorListener() {
                override fun onAnimationEnd() {
                    imageView.setImageBitmap(bitmap)
                    flip(axis, -90F, duration, object : ViewAnimatorListener() {
                        override fun onAnimationEnd() {
                            executeNext()
                        }
                    })
                }
            })
        }

        return this
    }

    fun flipAndSetImage(axis: Axis, @DrawableRes resId: Int, duration: Long = 500) : ImageViewAnimator {

        Rotation(imageView).apply {
            flip(axis, 90F, duration, object : ViewAnimatorListener() {
                override fun onAnimationEnd() {
                    imageView.setImageResource(resId)
                    flip(axis, -90F, duration, object : ViewAnimatorListener() {
                        override fun onAnimationEnd() {
                            executeNext()
                        }
                    })
                }
            })
        }

        return this
    }

    fun flipAndSetImage(axis: Axis, drawable: Drawable, duration: Long = 500) : ImageViewAnimator {

        Rotation(imageView).apply {
            flip(axis, 90F, duration, object : ViewAnimatorListener() {
                override fun onAnimationEnd() {
                    imageView.setImageDrawable(drawable)
                    flip(axis, -90F, duration, object : ViewAnimatorListener() {
                        override fun onAnimationEnd() {
                            executeNext()
                        }
                    })
                }
            })
        }

        return this
    }
}