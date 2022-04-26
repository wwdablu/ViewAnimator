package com.soumya.wwdablu.viewanimator.animators

import android.widget.ImageView
import com.soumya.wwdablu.viewanimator.ViewAnimator

class ImageViewAnimator constructor(private val imageView: ImageView) : BaseViewAnimator<ImageViewAnimator, ImageView>(), ViewAnimator {

    override fun getView(): ImageView {
        return imageView
    }

    override fun getViewAnimator(): ImageViewAnimator {
        return this
    }
}