package OpenGL;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class MyGLSurfaceView extends GLSurfaceView {

    private MyRenderer renderer;

    public MyGLSurfaceView(Activity activity, long gameLength) {
        super(activity.getApplicationContext());

        this.renderer = new MyRenderer(this, activity, gameLength * 1000);
        setEGLContextClientVersion(2);
        setRenderer(this.renderer);
        //setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (event != null)
            return renderer.onTouchEvent(event);

        else
            return super.onTouchEvent(event);
    }


    public MyRenderer getRenderer(){
        return renderer;
    }
}
