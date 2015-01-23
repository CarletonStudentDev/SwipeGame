package OpenGL;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.*;
import android.opengl.GLES20;
import android.util.Log;

import com.example.owner.gameproject.R;

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

    public static Bitmap decodeStringTexture(String str){

        Bitmap bitmap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bitmap);
        bitmap.eraseColor(0);

        Typeface tf = Typeface.create("Roboto",Typeface.NORMAL);
        Paint textPaint = new Paint();
        textPaint.setTypeface(tf);
        textPaint.setTextSize(200);
        textPaint.setAntiAlias(true);
        textPaint.setARGB(0xff, 0x00, 0x00, 0x00);
        canvas.drawText(str, 0, 400, textPaint);

        return bitmap;
    }
}