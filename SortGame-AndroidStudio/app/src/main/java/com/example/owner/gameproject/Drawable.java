package com.example.owner.gameproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class Drawable {

    public float[] mModelMatrix = new float[16];

    private String vertexShaderCode;
    private String fragmentShaderCode;

    private int uColorLocation;
    private int aPositionLocation;
    private int mMVPMatrixHandle;
    private int textureUniformHandle;
    private int texturePositionHandle;
    private int textureHandle;

    private int coordsPerVertex;

    private int program;

    private FloatBuffer vertexData;
    private FloatBuffer textureData;
    private ShortBuffer drawListBuffer;

    private float[] coords;
    private short[] drawOrder;
    private float[] color;
    private float[] textureCoords;

    private int drawType;

    private Context context;

    private boolean useTexture = false;

    public float x;
    public float y;

    public Drawable(Context context, int coordsPerVertex, int drawType) {
        this.drawType = drawType;
        this.coordsPerVertex = coordsPerVertex;
        this.context = context;
        Matrix.setIdentityM(mModelMatrix, 0);
    }

    public void initializeBuffers(){
        vertexData = ByteBuffer
                .allocateDirect(coords.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        vertexData
                .put(coords)
                .position(0);

        if(useTexture) {
            textureData = ByteBuffer
                    .allocateDirect(textureCoords.length * 4)
                    .order(ByteOrder.nativeOrder())
                    .asFloatBuffer();
            textureData
                    .put(textureCoords)
                    .position(0);
        }

        drawListBuffer = ByteBuffer
                .allocateDirect(drawOrder.length * 4)
                .order(ByteOrder.nativeOrder())
                .asShortBuffer();
        drawListBuffer
                .put(drawOrder)
                .position(0);
    }

    public void initializeShaderProgram(){
        program = ShaderHelper.createProgram(vertexShaderCode, fragmentShaderCode);
        GLES20.glUseProgram(program);
    }

    public void draw(float[] mvpMatrix){
        initializeShaderProgram();

        x = mModelMatrix[12];
        y = mModelMatrix[13];

        // pass Information pointers
        uColorLocation = GLES20.glGetUniformLocation(program, "vColor");
        aPositionLocation = GLES20.glGetAttribLocation(program, "vPosition");

        if(useTexture) {
            texturePositionHandle = GLES20.glGetAttribLocation(program, "a_TexCoordinate");
            textureUniformHandle = GLES20.glGetUniformLocation(program, "u_Texture");
        }

        GLES20.glVertexAttribPointer(aPositionLocation, coordsPerVertex, GLES20.GL_FLOAT, false, coordsPerVertex * 4, vertexData);
        GLES20.glEnableVertexAttribArray(aPositionLocation);

        if(useTexture) {
            GLES20.glVertexAttribPointer(texturePositionHandle, coordsPerVertex, GLES20.GL_FLOAT, true, coordsPerVertex * 4, textureData);
            GLES20.glEnableVertexAttribArray(texturePositionHandle);
        }

        GLES20.glUniform4fv(uColorLocation, 1, color, 0);

        if(useTexture) {
            GLES20.glEnable(GLES20.GL_BLEND);
            GLES20.glBlendFunc(GLES20.GL_ONE, GLES20.GL_ONE_MINUS_SRC_ALPHA);

            GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureHandle);
            GLES20.glUniform1i(textureUniformHandle, 0);
        }

        // projection for different sized screens
        mMVPMatrixHandle = GLES20.glGetUniformLocation(program, "uMVPMatrix");
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0);

        GLES20.glDrawElements(drawType, drawOrder.length, GLES20.GL_UNSIGNED_SHORT, drawListBuffer);
        GLES20.glDisableVertexAttribArray(aPositionLocation);
    }

    public void setShaderCode(String vertexShaderCode, String fragmentShaderCode){
        this.vertexShaderCode = vertexShaderCode;
        this.fragmentShaderCode = fragmentShaderCode;
    }

    public void setTexture(boolean useTexture, int resourceId){
        this.useTexture = useTexture;
        textureHandle = loadTexture(context, resourceId);
    }

    public void setCoords(float[] coords) {
        this.coords = coords;
    }

    public void setTextureCoords(float[] coords){
        this.textureCoords = coords;
    }

    public void setDrawOrder(short[] drawOrder){
        this.drawOrder = drawOrder;
    }

    public void setColor(int color){
        float red=   (color >> 16) & 0xFF;
        float green= (color >> 8) & 0xFF;
        float blue=  (color >> 0) & 0xFF;
        float alpha= (color >> 24) & 0xFF;
        this.color = new float[] {red / 255f, green / 255f, blue / 255f, alpha / 255f};
    }

    public static int loadTexture(final Context context, final int resourceId){
        final int[] textureHandle = new int[1];
        GLES20.glDeleteTextures(1,textureHandle,0);
        GLES20.glGenTextures(1, textureHandle, 0);
        if(textureHandle[0] != 0){
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inScaled = false;
            final Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureHandle[0]);
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);
            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
            bitmap.recycle();
        }
        if(textureHandle[0] == 0){
            throw new RuntimeException("Error loading texture.");
        }
        return textureHandle[0];
    }
}