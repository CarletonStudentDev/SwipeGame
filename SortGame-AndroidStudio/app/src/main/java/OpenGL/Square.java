package OpenGL;

import android.opengl.GLES20;

public class Square extends Drawable{

    private static final int COORDINATES_PER_VERTEX = 2;

    private static final String fragmentShaderCode = "precision mediump float;" +
                                                     "uniform sampler2D u_Texture;" +
                                                     "varying vec2 v_TexCoordinate;" +
                                                     "uniform vec4 vColor;" +
                                                     "void main() {" +
                                                                   "   gl_FragColor = vColor;" +
                                                                       //"   gl_FragColor = texture2D(u_Texture, v_TexCoordinate);" +
                                                                   "}",
                                 vertexShaderCode = "uniform mat4 uMVPMatrix;" +
                                                    "attribute vec4 vPosition;" +
                                                    "attribute vec2 a_TexCoordinate;" +
                                                    "varying vec2 v_TexCoordinate;" +
                                                    "void main() {" +
                                                                  "   v_TexCoordinate = a_TexCoordinate;" +
                                                                  "   gl_Position = uMVPMatrix * vPosition;" +
                                                                  "}";


    public float width,
                 length;


    private short[] drawOrder = { 0, 1, 2, 0, 2, 3 };



    public Square(float x, float y, float width, float length, float[] colorId) {
        super(COORDINATES_PER_VERTEX, GLES20.GL_TRIANGLES);
        this.width = width;
        this.length = length;

        width = width / 2;
        length = length / 2;

        float[] squareCoords = new float[]{
                x + width,  y + length,
                x + width,  y - length,
                x - width,  y - length,
                x - width,  y + length,};

        setCoords(squareCoords);
        setDrawOrder(drawOrder);
        setColor(colorId);
        setShaderCode(vertexShaderCode,fragmentShaderCode);
        initializeBuffers();
    }
}