package OpenGLEngine;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Class description
 *
 * @author Robert
 * @version version_number
 * @since 2015-02-06
 * Created by Robert on 06/02/2015.
 */
// The renderer object.
// Manages the graphic view / content
public class GDC11Renderer implements GLSurfaceView.Renderer {

    // OpenGL state stuff.
    private Shader mShader;
    private Camera mCamera;

    VBO mVBO1, mVBO2, mVBO3;

    private float[] mLightVector = { 2/3.f, 1/3.f, 2/3.f };  // Needs to be normalized
    private float[] mTransformedLightVector = new float[3];

    private void updateLightVector() {

        // Transform the light vector into model space. Since mViewMatrix
        // is orthogonal, the reverse transform can be done by multiplying
        // with the transpose.

        float[] viewMatrix = mCamera.viewMatrix();

        mTransformedLightVector[0] =
                viewMatrix[0] * mLightVector[0] +
                        viewMatrix[1] * mLightVector[1] +
                        viewMatrix[2] * mLightVector[2];
        mTransformedLightVector[1] =
                viewMatrix[4] * mLightVector[0] +
                        viewMatrix[5] * mLightVector[1] +
                        viewMatrix[6] * mLightVector[2];
        mTransformedLightVector[2] =
                viewMatrix[8] * mLightVector[0] +
                        viewMatrix[9] * mLightVector[1] +
                        viewMatrix[10] * mLightVector[2];
    }

    // This is called continuously to render.
    @Override
    public void onDrawFrame(GL10 unused) {

        mShader.use();
        mShader.clearView();
        mCamera.use(mShader);
        mShader.setLight(mTransformedLightVector);

        // VBO
        mShader.enableLight(true);

        mShader.setColor(red);
        mVBO1.draw();

        mShader.setColor(gold);
        mVBO2.draw();

        mShader.enableLight(false);
        mShader.setColor(brown);
        mVBO3.draw();

    }
    static float[] green = {0.2f,1,0.2f};
    static float[] brown = {0.7f,0.4f,0.2f};
    static float[] red = {0.9f,0,0};
    static float[] gold = {0.9f,0.8f,0.1f};
    static float[] black = {0,0,0};


    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        // CREATE GEOMETRY
        // NEVER load stuff on the render thread in real life!
        // You'd call fc.map() and b.load() on a loader thread, and
        // only then upload that to GL once it's done.

        mShader = new Shader();
        mCamera = new Camera();

        GeoData data = GeoData.halfpipe();
        mVBO1 = new VBO(data.mVertices, data.mIndices, GLES20.GL_TRIANGLE_STRIP, true, false, -1);

        data = GeoData.circle();
        mVBO2 = new VBO(data.mVertices, data.mIndices, GLES20.GL_TRIANGLE_FAN, true, false, -1);

        data = GeoData.grid();
        mVBO3 = new VBO(data.mVertices, data.mIndices, GLES20.GL_LINES, false, false, -1);
    }

    // This is called when the surface changes, e.g. after screen rotation.
    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        mCamera.perspective(width, height);

        updateLightVector();

        // Necessary if the manifest contains |android:configChanges="orientation"|.
        Shader.setViewPort(width, height);
    }
}