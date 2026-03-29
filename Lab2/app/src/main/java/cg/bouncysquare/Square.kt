package cg.bouncysquare

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.opengles.GL10
import javax.microedition.khronos.opengles.GL11

class Square {
    private var mFVertexBuffer: FloatBuffer
    private var mColorBuffer: ByteBuffer
    private var mIndexBuffer: ByteBuffer

    init {
        val vertices = floatArrayOf(
            -1.0f, -1.0f,
            1.0f, -1.0f,
            -1.0f,  1.0f,
            1.0f,  1.0f
        )

        val maxColor: Byte = 255.toByte()
        val colors = byteArrayOf(
            0, 0, 0, maxColor,
            maxColor, 0, 0, maxColor,
            0, 0, 0, maxColor,
            maxColor, 0, 0, maxColor
        )

        val indices = byteArrayOf(
            0, 3, 1,
            0, 2, 3
        )

        val vbb = ByteBuffer.allocateDirect(vertices.size * 4)
        vbb.order(ByteOrder.nativeOrder())
        mFVertexBuffer = vbb.asFloatBuffer()
        mFVertexBuffer.put(vertices)
        mFVertexBuffer.position(0)

        mColorBuffer = ByteBuffer.allocateDirect(colors.size)
        mColorBuffer.put(colors)
        mColorBuffer.position(0)

        mIndexBuffer = ByteBuffer.allocateDirect(indices.size)
        mIndexBuffer.put(indices)
        mIndexBuffer.position(0)
    }

    fun draw(gl: GL10) {
        gl.glFrontFace(GL11.GL_CW)
        gl.glVertexPointer(2, GL11.GL_FLOAT, 0, mFVertexBuffer)
        gl.glColorPointer(4, GL11.GL_UNSIGNED_BYTE, 0, mColorBuffer)
        gl.glDrawElements(GL11.GL_TRIANGLES, 6, GL11.GL_UNSIGNED_BYTE, mIndexBuffer)
        gl.glFrontFace(GL11.GL_CCW)
    }
}