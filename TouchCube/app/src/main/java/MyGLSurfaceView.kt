package com.example.touchcube

import android.content.Context
import android.opengl.GLSurfaceView
import android.view.MotionEvent

class MyGLSurfaceView(context: Context) : GLSurfaceView(context) {

    private val renderer: CubeRenderer
    private var previousX = 0f
    private var previousY = 0f

    init {
        renderer = CubeRenderer()
        setRenderer(renderer)

        // IMPORTANT pentru touch
        renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        if (event.action == MotionEvent.ACTION_MOVE) {
            val dx = x - previousX
            val dy = y - previousY

            renderer.angleX += dy
            renderer.angleY += dx

            // IMPORTANT: fără asta NU se mișcă
            requestRender()
        }

        previousX = x
        previousY = y
        return true
    }
}