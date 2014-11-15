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

    public static int createProgram(String vertexShaderCode,String fragmentShaderCode){
        int vertexShader = compileVertexShader(vertexShaderCode);
        int fragmentShader = compileFragmentShader(fragmentShaderCode);
        return linkProgram(vertexShader, fragmentShader);
    }
}