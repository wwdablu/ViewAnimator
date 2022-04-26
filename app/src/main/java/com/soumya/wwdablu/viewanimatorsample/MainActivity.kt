package com.soumya.wwdablu.viewanimatorsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.soumya.wwdablu.viewanimator.animators.Action
import com.soumya.wwdablu.viewanimator.animators.Direction
import com.soumya.wwdablu.viewanimator.animators.TextViewAnimator
import com.soumya.wwdablu.viewanimatorsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        TextViewAnimator(binding.tv).apply {
            typewrite("Please wait...").waitAndThen(1000) {
                slide(Action.Out, Direction.Right, 1000).then {
                    it.getView().text = ""
                    slide(Action.In, Direction.Left, 1000).then {
                        typewrite("Downloading assets...")
                    }
                }
            }
        }
    }
}