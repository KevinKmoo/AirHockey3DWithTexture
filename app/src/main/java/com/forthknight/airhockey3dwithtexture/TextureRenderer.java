package com.forthknight.airhockey3dwithtexture;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.forthknight.airhockey3dwithtexture.data.Mallet;
import com.forthknight.airhockey3dwithtexture.data.Table;
import com.forthknight.airhockey3dwithtexture.program.ColorShaderProgram;
import com.forthknight.airhockey3dwithtexture.program.TextureShaderProgram;
import com.forthknight.airhockey3dwithtexture.util.MatrixHelper;
import com.forthknight.airhockey3dwithtexture.util.TextureHelper;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glViewport;
import static android.opengl.Matrix.multiplyMM;
import static android.opengl.Matrix.rotateM;
import static android.opengl.Matrix.setIdentityM;
import static android.opengl.Matrix.translateM;

/**
 * Created by xiongyikai on 2017/4/24.
 */

public class TextureRenderer implements GLSurfaceView.Renderer{

    private final Context mContext;

    private float[] mProjectionMatrix = new float[16];
    private float[] mModelMatrix = new float[16];

    private Table mTable;
    private Mallet mMallet;

    private TextureShaderProgram mTextureShaderProgram;
    private ColorShaderProgram mColorShaderProgram;

    private int mTexture;

    public TextureRenderer(Context context){
        mContext = context;
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        mTable = new Table();
        mMallet = new Mallet();

        mTextureShaderProgram = new TextureShaderProgram(mContext);
        mColorShaderProgram = new ColorShaderProgram(mContext);

        mTexture = TextureHelper.loadTexture(mContext , R.drawable.air_hockey_surface);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        glViewport(0 , 0 , width , height);

        MatrixHelper.perspectiveM(mProjectionMatrix , 45 , (float)width / (float)height , 1f , 10f);

        setIdentityM(mModelMatrix, 0);
        translateM(mModelMatrix, 0, 0f, 0f, -2.5f);
        rotateM(mModelMatrix, 0 , -60 , 1 , 0 , 0);

        final float[] temp = new float[16];
        multiplyMM(temp, 0, mProjectionMatrix, 0, mModelMatrix, 0);
        System.arraycopy(temp, 0, mProjectionMatrix, 0, temp.length);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        glClear(GL_COLOR_BUFFER_BIT);

        mTextureShaderProgram.useProgram();
        mTextureShaderProgram.setUniforms(mProjectionMatrix , mTexture);
        mTable.bindData(mTextureShaderProgram);
        mTable.draw();

        mColorShaderProgram.useProgram();
        mColorShaderProgram.setUniforms(mProjectionMatrix);
        mMallet.bindData(mColorShaderProgram);
        mMallet.draw();

    }
}
