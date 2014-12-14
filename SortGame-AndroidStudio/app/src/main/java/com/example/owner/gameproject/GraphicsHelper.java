package com.example.owner.gameproject;

import android.content.Context;
import android.opengl.GLES20;

public class GraphicsHelper {

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

    public static float[] RGBArray(Context context, int colorId){
        int color = context.getResources().getColor(colorId);

        float red=   (color >> 16) & 0xFF;
        float green= (color >> 8) & 0xFF;
        float blue=  (color >> 0) & 0xFF;
        float alpha= (color >> 24) & 0xFF;
        return new float[] {red / 255f, green / 255f, blue / 255f, alpha / 255f};
    }
}