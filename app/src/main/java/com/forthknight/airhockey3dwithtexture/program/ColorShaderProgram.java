package com.forthknight.airhockey3dwithtexture.program;

import android.content.Context;

import com.forthknight.airhockey3dwithtexture.R;

import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glUniformMatrix4fv;

/**
 * Created by xiongyikai on 2017/4/24.
 */

public class ColorShaderProgram extends ShaderProgram{

    // Uniform locations
    private final int mUMatrixLocation;
    // Attribute locations
    private final int mAPositionLocation;
    private final int mAColorLocation;

    public ColorShaderProgram(Context context) {
        super(context, R.raw.simple_vertex_shader, R.raw.simple_fragment_shader);

        mUMatrixLocation = glGetUniformLocation(mProgram , U_MATRIX);

        mAColorLocation = glGetAttribLocation(mProgram , A_COLOR);
        mAPositionLocation = glGetAttribLocation(mProgram , A_POSITION);
    }


    public void setUniforms(float[] matrix) {
        // 把矩阵传递给渲染程序
       glUniformMatrix4fv(mUMatrixLocation, 1, false, matrix, 0);
    }

    public int getPositionAttributeLocation() {
        return mAPositionLocation;
    }
    public int getColorAttributeLocation() {
        return mAColorLocation;
    }
}
