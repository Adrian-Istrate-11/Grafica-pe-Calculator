package com.example.bouncycube

import android.opengl.GLSurfaceView
import kotlin.math.sin
import kotlin.math.tan
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class CubeRenderer : GLSurfaceView.Renderer {
    private val cube = Cube()
    private var transY = 0f
    private var angle = 0f

    override fun onDrawFrame(gl: GL10) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT or GL10.GL_DEPTH_BUFFER_BIT)

        gl.glMatrixMode(GL10.GL_MODELVIEW)
        gl.glLoadIdentity()

        gl.glTranslatef(0f, sin(transY), -6f)
        gl.glRotatef(angle, 1f, 1f, 0f)

        transY += 0.075f
        angle += 1.0f

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY)
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY)

        cube.draw(gl)
    }

    override fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {
        gl.glViewport(0, 0, width, height)

        gl.glMatrixMode(GL10.GL_PROJECTION)
        gl.glLoadIdentity()

        val ratio = width.toFloat() / height.toFloat()
        gl.glFrustumf(-ratio, ratio, -1f, 1f, 1f, 20f)

        gl.glMatrixMode(GL10.GL_MODELVIEW)
    }

    override fun onSurfaceCreated(gl: GL10, config: EGLConfig) {
        gl.glDisable(GL10.GL_DITHER)
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST)
        gl.glClearColor(0f, 0f, 0f, 1f)

        gl.glEnable(GL10.GL_CULL_FACE)
        gl.glShadeModel(GL10.GL_SMOOTH)
        gl.glEnable(GL10.GL_DEPTH_TEST)
    }
}