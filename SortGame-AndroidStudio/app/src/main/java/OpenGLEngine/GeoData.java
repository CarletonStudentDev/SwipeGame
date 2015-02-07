package OpenGLEngine;

import android.opengl.Matrix;
import android.util.Log;

/**
 * Class description
 *
 * @author Robert
 * @version version_number
 * @since 2015-02-06
 * Created by Robert on 06/02/2015.
 */
// Helper class to create some different geometries
public class GeoData {

    public float[] mVertices;
    public short[] mIndices;

    private GeoData() {}

    static public GeoData square(float x, float y, float width, float length){
        GeoData creator = new GeoData();
        float[] squareCoords = new float[]{
                //x         y       z              lighting
                x - width, -0.0f, y + length, 0.0f, -1.0f, 0.0f,// 0, Top Left
                x - width, -0.0f, y - length, 0.0f, -1.0f, 0.0f,// 1, Bottom Left
                x + width, -0.0f, y - length, 0.0f, -1.0f, 0.0f,// 2, Bottom Right
                x + width, -0.0f, y + length, 0.0f, -1.0f, 0.0f,// 3, Top Right
        };
        short[] drawOrder = { 0, 1, 2, 0, 2, 3 };
        creator.mVertices = squareCoords;
        creator.mIndices = drawOrder;
        return creator;
    }

    static public GeoData image(float x, float y, float width, float length){
        GeoData creator = new GeoData();
        float[] squareCoords = new float[]{
                //x         y       z              lighting        texture
                x - width, -0.0f, y + length, 0.0f, -1.0f, 0.0f, 0.0f, 1.0f,// 0, Top Left
                x - width, -0.0f, y - length, 0.0f, -1.0f, 0.0f, 1.0f, 1.0f,// 1, Bottom Left
                x + width, -0.0f, y - length, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f,// 2, Bottom Right
                x + width, -0.0f, y + length, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f,// 3, Top Right
        };
        short[] drawOrder = { 0, 1, 2, 0, 2, 3 };
        creator.mVertices = squareCoords;
        creator.mIndices = drawOrder;
        return creator;
    }

    static public GeoData halfpipe() {
        GeoData creator = new GeoData();
        creator.mVertices = createVertices1(44);
        creator.mIndices = createIndices1(44);
        return creator;
    }

    static public GeoData circle(float x, float y, float radius) {
        GeoData creator = new GeoData();
        creator.mVertices = createCircleVertices(32, x, y);
        creator.mIndices = createCircleIndices(32);
        return creator;
    }

    static public GeoData grid() {
        GeoData creator = new GeoData();
        creator.mVertices = createGridVertices(30,30);
        creator.mIndices = createGridIndices(30,30);
        return creator;
    }

    static float[] createGridVertices(int m, int n) {
        float[] vertices = new float[3*(2*m + 2*n + 4)];

        float y = 0.1f;
        float S = 2.8f;
        for (int i=0; i<=m; i++) {
            float x = S*(float) (-0.5 + (1.0*i)/m);
            float z = S*0.5f;
            vertices[6*i + 0] = x;
            vertices[6*i + 1] = y;
            vertices[6*i + 2] = z;
            vertices[6*i + 3] = x;
            vertices[6*i + 4] = y;
            vertices[6*i + 5] = -z;
        }

        int start = 3*(2*m + 2);
        // start = 0;
        for (int i=0; i<=n; i++) {
            float z = S*(float) (-0.5 + (1.0*i)/n);
            float x = S*0.5f;
            vertices[start + 6*i + 0] = x;
            vertices[start + 6*i + 1] = y;
            vertices[start + 6*i + 2] = z;
            vertices[start + 6*i + 3] = -x;
            vertices[start + 6*i + 4] = y;
            vertices[start + 6*i + 5] = z;
        }

        float[] M = new float[16];
        Matrix.setIdentityM(M, 0);
        Matrix.rotateM(M, 0, 27, 0.76f, -0.9f, 1.5f);
        int count = (2*m + 2*n + 4);
        Log.d("MKZ", "A: " + count);
        Log.d("MKZ", "B: " + vertices.length / 3);
        for (int i=0; i<count-1; i++) {
            int offset = 3*i;
            Log.d("MKZ", "offset: " + offset);
            Matrix.multiplyMV(vertices, offset, M, 0, vertices, offset);
        }

        return vertices;
    }

    static short[] createGridIndices(int m, int n) {
        int N = 2*(m+n+2);
        short[] indices = new short[N];
        for (int i=0; i<N; i++) {
            indices[i] = (short)i;
        }
        return indices;
    }

    static float[] createVertices1(int n) {
        int NUM_COMPONENTS = 6;
        float S = 0.75f;
        float X = 1f;
        float z0 = 1.3f;
        float z1 = 1.1f;
        float dx = 2*X / n;
        float[] vertices = new float[NUM_COMPONENTS*(n+1)*2];
        for (int i=0; i<(n+1); i++) {
            int I0 = 2*NUM_COMPONENTS*i;
            int I1 = 2*NUM_COMPONENTS*i + NUM_COMPONENTS;
            float x = -X + dx*i;
            float y = -(float) Math.sqrt(1.0 - x*x);
            vertices[I0 + 0] = S*x;
            vertices[I0 + 1] = S*y;
            vertices[I0 + 2] = S*z0;
            vertices[I0 + 3] = x;
            vertices[I0 + 4] = y;
            vertices[I0 + 5] = 0;

            vertices[I1 + 0] = S*x;
            vertices[I1 + 1] = S*y;
            vertices[I1 + 2] = S*z1;
            vertices[I1 + 3] = x;
            vertices[I1 + 4] = y;
            vertices[I1 + 5] = 0;
        }
        return vertices;
    }
    static short[] createIndices1(int n) {
        short[] indices = new short[(n+1)*2];
        for (short i=0; i<(n+1)*2; i++) {
            indices[i] = i;
        }
        return indices;
    }

    static float[] createCircleVertices(int n, float posX, float posY) {
        int NUM_COMPONENTS = 6;
        float[] vertices = new float[NUM_COMPONENTS*(n+2)];
        final float S = 0.9f;
        final float Y = -0.0f;
        vertices[0] = posX;
        vertices[1] = Y;
        vertices[2] = posY;
        vertices[3] = 0;
        vertices[4] =-1;
        vertices[5] = 0;
        for (int i=0; i<=n; i++) {
            int I = 6 + 6*i;
            float a = (float) (2*Math.PI*i/n);
            float x = (float) (S*Math.cos(a)+posX);
            float z = (float) (S*Math.sin(a)+posY);
            vertices[I+0] = x;
            vertices[I+1] = Y;
            vertices[I+2] = z;
            vertices[I+3] = 0;
            vertices[I+4] =-1;
            vertices[I+5] = 0;
        }
        return vertices;
    }
    static short[] createCircleIndices(int n) {
        short[] indices = new short[(n+2)];
        for (short i=0; i<(n+2); i++) {
            indices[i] = i;
        }
        return indices;
    }
}