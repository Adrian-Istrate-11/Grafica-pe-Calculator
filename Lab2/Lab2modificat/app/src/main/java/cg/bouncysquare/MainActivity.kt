package cg.bouncysquare

import android.app.Activity
import android.opengl.GLSurfaceView
import android.os.Bundle
import android.view.WindowManager

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val view = GLSurfaceView(this)
        view.setRenderer(SquareRenderer())

        setContentView(view)
    }
}