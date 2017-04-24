package com.forthknight.airhockey3dwithtexture.data;

import com.forthknight.airhockey3dwithtexture.Constants;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glVertexAttribPointer;

/**
 * Created by xiongyikai on 2017/4/24.
 */

public class VertexArray {

    private final FloatBuffer mFloatBuffer;

    public VertexArray(float[] data){
        mFloatBuffer = ByteBuffer
                .allocateDirect(Constants.BYTE_PRE_FLOAT * data.length)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        mFloatBuffer.put(data);
    }

    public void setVertexAttribPointer(int dataOffset, int attributeLocation, int componentCount, int stride){
        mFloatBuffer.position(dataOffset);

        glVertexAttribPointer(attributeLocation, componentCount, GL_FLOAT,
                false, stride, mFloatBuffer);
        glEnableVertexAttribArray(attributeLocation);

        mFloatBuffer.position(0);
    }

}
