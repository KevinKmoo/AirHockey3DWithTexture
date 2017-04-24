package com.forthknight.airhockey3dwithtexture.program;

import android.content.Context;

import com.forthknight.airhockey3dwithtexture.util.ShaderHelper;
import com.forthknight.airhockey3dwithtexture.util.TextResouceReader;

import static android.opengl.GLES20.glUseProgram;

/**
 * Created by xiongyikai on 2017/4/24.
 */

public class ShaderProgram {

    // Uniform constants
    protected static final String U_MATRIX = "u_Matrix";
    protected static final String U_TEXTURE_UNIT = "u_TextureUnit";
    // Attribute constants
    protected static final String A_POSITION = "a_Position";
    protected static final String A_COLOR = "a_Color";
    protected static final String A_TEXTURE_COORDINATES = "a_TextureCoordinates";
    // Shader program
    protected final int mProgram;
    protected ShaderProgram(Context context, int vertexShaderResourceId,
                            int fragmentShaderResourceId) {
        // Compile the shaders and link the program.
        mProgram = ShaderHelper.buildProgram(
                TextResouceReader.readTextFileFromResource(context, vertexShaderResourceId),
                TextResouceReader.readTextFileFromResource(context, fragmentShaderResourceId));
    }

    public void useProgram() {
        // Set the current OpenGL shader program to this program.
        glUseProgram(mProgram);
    }

}
