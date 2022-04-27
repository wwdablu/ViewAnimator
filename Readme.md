[![](https://jitpack.io/v/wwdablu/ViewAnimator.svg)](https://jitpack.io/#wwdablu/ViewAnimator)  
# ViewAnimator

ViewAnimator is an Android library that has been developed with the aim to perform basic animation quickly without the need for any major coding. All the boiler plate code will be removed when using this library and the developer can concentrate on only the functionality.

For example, the below code:
```
textViewAnimator
.typewrite("Android animation library")
.then(waitFor = 1000) {
    imageViewAnimator.flipAndSetImage(Axis.X, R.drawable.ic_love, 300)
    textViewAnimator.typewrite("ViewAnimator")
        .then(waitFor = 1000) {
            var heartBeat = 0
            imageViewAnimator.loop({ viewAnimator, callback ->
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
```
produces the following result:  
![Result](https://github.com/wwdablu/ViewAnimator/blob/master/sample/Capture.gif)  


## Usage  
Put the following on the gradle:  
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
    
dependencies {
    implementation 'com.github.wwdablu:ViewAnimator:1.0.0'
}
```
Currently the library supports two (2) ViewAnimators:  
- [x] TextViewAnimator
- [x] ImageViewAnimator  
  
The developer can extend the BaseViewAnimator class to write their own custom view animators too. Following are the methods which are provided by the ViewAnimators.  
  
|Method                                                          |ViewAnimator                  |Description                        |
|----------------------------------------------------------------|------------------------------|-----------------------------------|
|move(pixels: Float, on: Axis, duration: Long = 500)             |Base                          |Moves the view on the provided axis to the specified pixel|
|slide(type: Action, direction: Direction, duration: Long = 500) |Base                          |Slide on or out the view on an axis from a provided direction|
|fade(type: Action, duration: Long = 500)                        |Base                          |Fade (in/out) the view|
|scale(type: Action, duration: Long = 500)                       |Base                          |Scale (in/out) the view|
|scale(to: Float, duration: Long = 500)                          |Base                          |Scale the view to a provided value|
|rotate(degree: Float, by: Rotate, duration: Long = 500)         |Base                          |Rotate the view by a degree either CW or CCW|
|flip(axis: Axis, degree: Float, duration: Long = 500)           |Base                          |Flip the view by a degree on the pivot axis|
|typewrite(text: String, typeGap: Long = 33)                     |TextViewAnimator              |Performs a typing animation|
|removeText(removeGap: Long = 33)                                |TextViewAnimator              |Performs a text removal animation|
|typewriteAndRemoveMany(strings: Array<String>, typeGap: Long = 33, removeGap: Long = 33, wait: Long = 1000)|TextViewAnimator|Performs typewrite animation of several strings|
|flipAndSetImage(axis: Axis, bitmap: Bitmap, duration: Long = 500)|ImageViewAnimator            |Flip the view and sets a different image|
|flipAndSetImage(axis: Axis, @DrawableRes resId: Int, duration: Long = 500)|ImageViewAnimator   |Flip the view and sets a different image|
|flipAndSetImage(axis: Axis, drawable: Drawable, duration: Long = 500)|ImageViewAnimator        |Flip the view and sets a different image|
  
