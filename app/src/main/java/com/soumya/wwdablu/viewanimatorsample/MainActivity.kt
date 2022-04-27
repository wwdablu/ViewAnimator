package com.soumya.wwdablu.viewanimatorsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.soumya.wwdablu.viewanimator.animators.Axis
import com.soumya.wwdablu.viewanimator.animators.ImageViewAnimator
import com.soumya.wwdablu.viewanimator.animators.TextViewAnimator
import com.soumya.wwdablu.viewanimatorsample.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ivAnimator = ImageViewAnimator(binding.iv)
        val tvAnimator = TextViewAnimator(binding.tv)

        CoroutineScope(Dispatchers.Main).launch {

            delay(1000)
            tvAnimator
                .typewrite("Android animation library")
                .then(waitFor = 1000) {
                    ivAnimator.flipAndSetImage(Axis.X, R.drawable.ic_love, 300)
                    tvAnimator.typewrite("ViewAnimator")
                        .then(waitFor = 1000) {
                            var heartBeat = 0
                            ivAnimator.loop({ viewAnimator, callback ->

                                viewAnimator.scale(2F, 100).then {
                                    it.scale(1F, 100).then {
                                        callback.done()
                                    }
                                }
                            }, fun(): Boolean {
                                return heartBeat++ != 2
                            }, {})
                        }
                }
        }
    }
}