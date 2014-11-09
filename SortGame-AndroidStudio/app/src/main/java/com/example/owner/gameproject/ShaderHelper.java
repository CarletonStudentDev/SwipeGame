package com.example.owner.gameproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;

/**
 * Created by home on 06/11/2014.
 */
public class ShaderHelper {

    private static String fragmentShaderCode =
            "precision mediump float;" +
            "uniform sampler2D u_Texture;" +
            "varying vec2 v_TexCoordinate;" +
            "uniform vec4 vColor;" +
            "void main() {" +
            //"   gl_FragColor = vColor;" +
            "   gl_FragColor = texture2D(u_Texture, v_TexCoordinate);" +
            "}";
    private static String vertexShaderCode =
            "uniform mat4 uMVPMatrix;" +
            "attribute vec4 vPosition;" +
            "attribute vec2 a_TexCoordinate;" +
            "varying vec2 v_TexCoordinate;" +
            "void main() {" +
            "   v_TexCoordinate = a_TexCoordinate;" +
            "   gl_Position = uMVPMatrix * vPosition;" +
            "}";

    public static int compileVertexShader(String shaderCode){
        return compileShader(GLES20.GL_VERTEX_SHADER,shaderCode);
    }
    public static int compileFragmentShader(String shaderCode){
        return compileShader(GLES20.GL_FRAGMENT_SHADER,shaderCode);
    }

    private static int compileShader(int type, String shaderCode){
        final int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader,shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }

    public static int linkProgram(int vertexShaderId, int fragmentShaderId){
        final int program = GLES20.glCreateProgram();

        GLES20.glAttachShader(program,vertexShaderId);
        GLES20.glAttachShader(program,fragmentShaderId);
        GLES20.glLinkProgram(program);

        return program;
    }
    public static int createProgram(){
        int vertexShader = compileVertexShader(vertexShaderCode);
        int fragmentShader = compileFragmentShader(fragmentShaderCode);
        return linkProgram(vertexShader, fragmentShader);
    }

    public static int loadTexture(final Context context, final int resourceId){
        final int[] textureHandle = new int[1];
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