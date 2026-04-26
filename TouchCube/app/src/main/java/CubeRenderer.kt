package com.example.touchcube

import javax.microedition.khronos.opengles.GL10
import javax.microedition.khronos.egl.EGLConfig
import android.opengl.GLSurfaceView

class CubeRenderer : GLSurfaceView.Renderer {

    private val cube = Cube()

    var angleX = 0f
    var angleY = 0f

    override fun onDrawFrame(gl: GL10) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT or GL10.GL_DEPTH_BUFFER_BIT)

        gl.glMatrixMode(GL10.GL_MODELVIEW)
        gl.glLoadIdentity()

        gl.glTranslatef(0f, 0f, -6f)

        gl.glRotatef(angleX, 1f, 0f, 0f)
        gl.glRotatef(angleY, 0f, 1f, 0f)

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY)
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY)

        cube.draw(gl)
    }

    override fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {
        gl.glViewport(0, 0, width, height)

        gl.glMatrixMode(GL10.GL_PROJECTION)
        gl.glLoadIdentity()

        val ratio = width.toFloat() / height
        gl.glFrustumf(-ratio, ratio, -1f, 1f, 1f, 10f)
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