package OpenGL;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class MyGLSurfaceView extends GLSurfaceView {

    Context context;
    MyRenderer renderer;

    private float mPreviousX;
    private float mPreviousY;

    public MyGLSurfaceView(Context context) {
        super(context);
        this.context = context;

        renderer = new MyRenderer(context.getResources(),this);
        setEGLContextClientVersion(2);
        setRenderer(renderer);
        //setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event != null)
            return renderer.onTouchEvent(event);

        else
            return super.onTouchEvent(event);
    }


    public MyRenderer getRenderer(){
        return renderer;
    }
}
