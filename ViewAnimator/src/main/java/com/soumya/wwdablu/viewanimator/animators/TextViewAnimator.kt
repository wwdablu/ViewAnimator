package com.soumya.wwdablu.viewanimator.animators

import android.annotation.SuppressLint
import android.widget.TextView
import com.soumya.wwdablu.viewanimator.ViewAnimator
import kotlinx.coroutines.*

class TextViewAnimator constructor(private val textView: TextView) : BaseViewAnimator<TextViewAnimator, TextView>(), ViewAnimator {

    override fun getView(): TextView {
        return textView;
    }

    override fun getViewAnimator(): TextViewAnimator {
        return this
    }

    @SuppressLint("SetTextI18n")
    fun typewrite(text: String, typeGap: Long = 33) : TextViewAnimator {

        textView.text = ""

        CoroutineScope(Dispatchers.IO).launch {
            text.forEach { char ->
                delay(typeGap)
                withContext(Dispatchers.Main) {
                    textView.text = "${textView.text}$char"
                }
            }

            withContext(Dispatchers.Main) {
                executeNext()
            }
        }

        return this
    }

    fun removeText(removeGap: Long = 33) : TextViewAnimator {

        CoroutineScope(Dispatchers.IO).launch {
            textView.apply {

                while (text.isNotEmpty()) {
                    delay(removeGap)
                    withContext(Dispatchers.Main) {
                        text = text.substring(0, text.length - 1)
                    }
                }

                withContext(Dispatchers.Main) {
                    text = ""
                    executeNext()
                }
            }
        }

        return this
    }

    fun typewriteAndRemoveMany(strings: Array<String>,
                               typeGap: Long = 33,
                               removeGap: Long = 33,
                               wait: Long = 1000) : TextViewAnimator {

        var arrayIndex = 0

        loop({ _, callback ->
            typewrite(strings[arrayIndex], typeGap).then(wait) {
                removeText(removeGap).then(wait) {
                    arrayIndex++
                    callback.done()
                }
            }
        }, fun() : Boolean {
            return arrayIndex < strings.size
        }, {
            //
        })

        return this
    }
}