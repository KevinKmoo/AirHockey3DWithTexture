package com.forthknight.airhockey3dwithtexture.data;

import com.forthknight.airhockey3dwithtexture.Constants;
import com.forthknight.airhockey3dwithtexture.program.ColorShaderProgram;

import static android.opengl.GLES20.GL_POINTS;
import static android.opengl.GLES20.glDrawArrays;

/**
 * Created by xiongyikai on 2017/4/24.
 */

public class Mallet {

    private static final int POSITION_COMPONENT_COUNT = 2;
    private static final int COLOR_COMPONENT_COUNT = 3;
    private static final int STRIDE = (POSITION_COMPONENT_COUNT + COLOR_COMPONENT_COUNT)
            * Constants.BYTE_PRE_FLOAT;

    private static final float[] VERTEXT_DATA = new float[]{
            // Order of coordinates: X, Y, R, G, B
            0f, -0.4f, 0f, 0f, 1f,
            0f, 0.4f, 1f, 0f, 0f
    };

    private VertexArray mVertexData;

    public Mallet(){
        mVertexData = new VertexArray(VERTEXT_DATA);
    }

    public void bindData(ColorShaderProgram program){
        mVertexData.setVertexAttribPointer(0 ,
                program.getPositionAttributeLocation() ,
                POSITION_COMPONENT_COUNT , STRIDE);
        mVertexData.setVertexAttribPointer(POSITION_COMPONENT_COUNT ,
                program.getColorAttributeLocation(),
                COLOR_COMPONENT_COUNT , STRIDE);
    }

    public void draw(){
        glDrawArrays(GL_POINTS, 0, 2);
    }

}
