package com.example.bouncycube

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.opengles.GL10
import javax.microedition.khronos.opengles.GL11

class Cube {
    private var vertexBuffer: FloatBuffer
    private var colorBuffer: ByteBuffer
    private var indexBuffer: ByteBuffer

    init {
        val vertices = floatArrayOf(
            -1f,  1f,  1f,
            1f,  1f,  1f,
            1f, -1f,  1f,
            -1f, -1f,  1f,
            -1f,  1f, -1f,
            1f,  1f, -1f,
            1f, -1f, -1f,
            -1f, -1f, -1f
        )

        val max = 255.toByte()
        val colors = byteArrayOf(
            max, 0, 0, max,
            max, 0, 0, max,
            max, 0, 0, max,
            max, 0, 0, max,
            0, 0, max, max,
            0, 0, max, max,
            0, 0, max, max,
            0, 0, max, max
        )

        val indices = byteArrayOf(
            0, 1, 2, 3,
            4, 5, 6, 7,
            0, 4, 7, 3,
            1, 5, 6, 2,
            0, 1, 5, 4,
            3, 2, 6, 7
        )

        val vbb = ByteBuffer.allocateDirect(vertices.size * 4)
        vbb.order(ByteOrder.nativeOrder())
        vertexBuffer = vbb.asFloatBuffer()
        vertexBuffer.put(vertices)
        vertexBuffer.position(0)

        colorBuffer = ByteBuffer.allocateDirect(colors.size)
        colorBuffer.put(colors)
        colorBuffer.position(0)

        indexBuffer = ByteBuffer.allocateDirect(indices.size)
        indexBuffer.put(indices)
        indexBuffer.position(0)
    }

    fun draw(gl: GL10) {
        gl.glVertexPointer(3, GL11.GL_FLOAT, 0, vertexBuffer)
        gl.glColorPointer(4, GL11.GL_UNSIGNED_BYTE, 0, colorBuffer)

        for (i in 0 until 6) {
            indexBuffer.position(i * 4)
            gl.glDrawElements(GL11.GL_TRIANGLE_FAN, 4, GL11.GL_UNSIGNED_BYTE, indexBuffer)
        }

        indexBuffer.position(0)
    }
}