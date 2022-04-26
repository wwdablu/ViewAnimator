ViewAnimator

ViewAnimator is an Android library that has been developed with the aim to perform basic animation
quickly without the need for any major coding. All the boiler plate code will be removed when using
this library and the developer can concentrate on only the functionality.

For example, the below code produces the following result:
``
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
``
