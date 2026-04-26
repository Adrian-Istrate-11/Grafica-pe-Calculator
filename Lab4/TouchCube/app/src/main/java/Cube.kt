package com.example.touchcube

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.opengles.GL10

class Cube {

    private val vertexBuffer: FloatBuffer
    private val colorBuffer: FloatBuffer

    private val vertices = floatArrayOf(
        -1f, -1f,  0f,
        1f, -1f,  0f,
        1f,  1f,  0f,
        -1f,  1f,  0f
    )

    private val colors = floatArrayOf(
        0f, 1f, 0f, 1f,
        0f, 1f, 0f, 1f,
        0f, 1f, 0f, 1f,
        0f, 1f, 0f, 1f
    )

    init {
        val vbb = ByteBuffer.allocateDirect(vertices.size * 4)
        vbb.order(ByteOrder.nativeOrder())
        vertexBuffer = vbb.asFloatBuffer()
        vertexBuffer.put(vertices)
        vertexBuffer.position(0)

        val cbb = ByteBuffer.allocateDirect(colors.size * 4)
        cbb.order(ByteOrder.nativeOrder())
        colorBuffer = cbb.asFloatBuffer()
        colorBuffer.put(colors)
        colorBuffer.position(0)
    }

    fun draw(gl: GL10) {
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer)
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer)

        gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 4)
    }
}