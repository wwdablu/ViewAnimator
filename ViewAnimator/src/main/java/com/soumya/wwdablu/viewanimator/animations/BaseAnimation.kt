package com.soumya.wwdablu.viewanimator.animations

import android.view.View
import android.view.ViewGroup

internal abstract class BaseAnimation internal constructor() {

    protected fun getTopMargin(view: View) : Float {
        return view.y
    }

    protected fun getLeftMargin(view: View) : Float {
        return view.x
    }

    protected fun getRightMargin(view: View) : Float {

        val parentView = view.parent as ViewGroup
        return parentView.width - (view.x + view.width)
    }

    protected fun getBottomMargin(view: View) : Float {

        val parentView = view.parent as ViewGroup
        return parentView.height - (view.y + view.height)
    }
}