package OpenGLEngine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;

/**
 * Class description
 *
 * @author Robert
 * @version version_number
 * @since 2015-02-06
 * Created by Robert on 06/02/2015.
 */
// all GLES20 calls are made here
public class Shader {
    // THESE ARE ARBITRARY VALUES, the only constraints are
    // - must be different
    // - must be less than a maximum value
    static final int VERTEX_POS = 0;
    static final int NORMAL_POS = 3;
    static final int TEX_POS = 7;
    static final String TAG = "VBOTest";

    private int mProgramId;
    private int mTextureLoc;
    private int mViewProjectionLoc;
    private int mLightVectorLoc;
    private int mColorLoc;
    private int mEnableLightLoc;
    private int mEnableTexLoc;


    public Shader() {
        mProgramId = loadProgram(kVertexShader, kFragmentShader);
        GLES20.glBindAttribLocation(mProgramId, Shader.VERTEX_POS, "position");
        GLES20.glBindAttribLocation(mProgramId, Shader.NORMAL_POS, "normal");
        GLES20.glLinkProgram(mProgramId);
        mViewProjectionLoc =
                GLES20.glGetUniformLocation(mProgramId, "worldViewProjection");
        mLightVectorLoc =
                GLES20.glGetUniformLocation(mProgramId, "lightVector");
        mColorLoc =
                GLES20.glGetUniformLocation(mProgramId, "color");
        mEnableLightLoc =
                GLES20.glGetUniformLocation(mProgramId, "enableLight");
        mEnableTexLoc =
                GLES20.glGetUniformLocation(mProgramId, "enableTexture");
        mTextureLoc =
                GLES20.glGetUniformLocation(mProgramId, "textureSampler");

        // Other state.
        GLES20.glClearColor(0.7f, 0.7f, 0.7f, 1.0f);
        GLES20.glEnable(GLES20.GL_CULL_FACE);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
    }

    public void use() {
        GLES20.glUseProgram(mProgramId);
    }
    public void setCamera(float[] viewProjectionMatrix) {
        GLES20.glUniformMatrix4fv(mViewProjectionLoc,
                1,
                false, // transpose isn't supported
                viewProjectionMatrix, 0);
    }
    public void setLight(float[] transformedLightVector) {
        GLES20.glUniform3fv(mLightVectorLoc, 1, transformedLightVector, 0);
    }
    public void setColor(float[] color) {
        GLES20.glUniform3fv(mColorLoc, 1, color, 0);
    }
    public void enableLight(boolean val) {
        GLES20.glUniform1i(mEnableLightLoc, val ? 1 : 0);
    }
    public void enableTexture(boolean val) {
        GLES20.glUniform1i(mEnableTexLoc, val ? 1 : 0);
        if(val) GLES20.glBlendFunc(GLES20.GL_ONE, GLES20.GL_ONE_MINUS_SRC_ALPHA);
    }
    public void setTexture(Context context, int bitmapId){
        enableTexture(true);
        GLES20.glEnable(GLES20.GL_TEXTURE_2D);
        GLES20.glEnable(GLES20.GL_BLEND);


        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, loadTexture(context, bitmapId));
        GLES20.glUniform1i(mTextureLoc, 0);

    }

    static public void setViewPort(int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }



    private static String kLogTag = "GDC11";

    private static int getShader(String source, int type) {
        int shader = GLES20.glCreateShader(type);
        if (shader == 0) return 0;

        GLES20.glShaderSource(shader, source);
        GLES20.glCompileShader(shader);
        int[] compiled = { 0 };
        GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, 0);
        if (compiled[0] == 0) {
            Log.e(kLogTag, GLES20.glGetShaderInfoLog(shader));
        }
        return shader;
    }

    public static int loadProgram(String vertexShader,
                                  String fragmentShader) {
        int vs = getShader(vertexShader, GLES20.GL_VERTEX_SHADER);
        int fs = getShader(fragmentShader, GLES20.GL_FRAGMENT_SHADER);
        if (vs == 0 || fs == 0) return 0;

        int program = GLES20.glCreateProgram();
        GLES20.glAttachShader(program, vs);
        GLES20.glAttachShader(program, fs);
        GLES20.glLinkProgram(program);

        int[] linked = { 0 };
        GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, linked, 0);
        if (linked[0] == 0) {
            Log.e(kLogTag, GLES20.glGetProgramInfoLog(program));
            return 0;
        }
        return program;
    }

    /**
     * loads the texture into memory and returns a pointer to it's location in memory
     *
     * @param context: reference to the resources
     * @param bitmapId: texture specific id
     * @return: returns textureHandle which holds the location of the texture in memory
     */
    public static int loadTexture(Context context, int bitmapId){
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), bitmapId);

        final int[] textureHandle = new int[1];
        GLES20.glDeleteTextures(1,textureHandle,0);
        GLES20.glGenTextures(1, textureHandle, 0);
        //check if the textureHandle is already pointing to something
        if(textureHandle[0] != 0){
            // set textureHandle to point to bitmap
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureHandle[0]);
            //telling OpenGL how to render the texture
            //GL_NEAREST -> nearest pixel (no blur)
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);
            //tells OpenGL how to render the texture on the shape
            //GL_CLAMP_TO_EDGE -> clamp edge of texture to the edge of the shape
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);
            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
            bitmap.recycle();
        }
        if(textureHandle[0] == 0){  //error handling
            throw new RuntimeException("Error loading texture.");
        }
        return textureHandle[0];
    }

    private static final String kVertexShader =
            "precision mediump float;                                   \n" +
                    "uniform mat4 worldViewProjection;                          \n" +
                    "uniform vec3 lightVector;                                  \n" +
                    "attribute vec3 position;                                   \n" +
                    "attribute vec3 normal;                                     \n" +
                    "varying float light;                                       \n" +
                    "void main() {                                              \n" +
                    // |lightVector| is in the model space, so the model
                    // doesn't have to be transformed.
                    "  light = max(dot(normal, lightVector), 0.0) + 0.2;        \n" +
                    "  gl_Position = worldViewProjection * vec4(position, 1.0); \n" +
                    "}";

    private static final String kFragmentShader =
                    "precision mediump float;                                                   \n" +
                    "uniform sampler2D textureSampler;                                          \n" +
                    "varying vec2 textureCoordinate;                                            \n" +
                    "uniform vec3 color;                                                        \n" +
                    "uniform int enableLight;                                                   \n" +
                    "uniform int enableTexture;                                                 \n" +
                    "varying float light;                                                       \n" +
                    "void main() {                                                              \n" +
                    "  if (1 == enableTexture) {                                                \n" +
                    "    if (1 == enableLight) {                                                \n" +
                    "      gl_FragColor = light * texture2D(textureSampler, textureCoordinate); \n" +
                    "    } else {                                                               \n" +
                    "      gl_FragColor = texture2D(textureSampler, textureCoordinate);         \n" +
                    "    }                                                                      \n" +
                    "  } else {                                                                 \n" +
                    "    if (1 == enableLight) {                                                \n" +
                    "      gl_FragColor = light * vec4(color,1);                                \n" +
                    "    } else {                                                               \n" +
                    "      gl_FragColor = vec4(color,1);                                        \n" +
                    "    }                                                                      \n" +
                    "  }                                                                        \n" +
                    "                                                                           \n" +
                    // "  gl_FragColor = light * vec4(0.1,0.7,0.0,1);                           \n" +
                    "}";


    public void clearView() {
        int clearMask = GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT;
        GLES20.glClear(clearMask);
    }
}
