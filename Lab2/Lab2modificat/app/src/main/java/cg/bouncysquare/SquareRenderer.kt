package cg.bouncysquare

import javax.microedition.khronos.opengles.GL10
import javax.microedition.khronos.egl.EGLConfig
import android.opengl.GLSurfaceView
import kotlin.math.sin

class SquareRenderer : GLSurfaceView.Renderer {

    private var mSquare: Square = Square()
    private var mTransY: Float = 0f

    override fun onDrawFrame(gl: GL10) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT or GL10.GL_DEPTH_BUFFER_BIT)

        gl.glMatrixMode(GL10.GL_MODELVIEW)
        gl.glLoadIdentity()

        gl.glTranslatef(0.0f, sin(mTransY), -3.0f)
        mTransY += 0.3f

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY)
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY)

        mSquare.draw(gl)
    }

    override fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {
        gl.glViewport(0, 0, width, height)

        gl.glMatrixMode(GL10.GL_PROJECTION)
        gl.glLoadIdentity()

        val ratio = width.toFloat() / height.toFloat()
        gl.glFrustumf(-ratio, ratio, -1f, 1f, 1f, 10f)
    }

    override fun onSurfaceCreated(gl: GL10, config: EGLConfig) {


        // gl.glDisable(GL10.GL_DITHER)

        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST)

        //  fundal schimbat
        gl.glClearColor(0.2f, 0.2f, 0.4f, 0.8f)

        gl.glEnable(GL10.GL_CULL_FACE)
        gl.glShadeModel(GL10.GL_SMOOTH)
        gl.glEnable(GL10.GL_DEPTH_TEST)
    }
}