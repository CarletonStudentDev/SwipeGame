package carletonstudentdev.googleplayservices;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class GLSurf extends GLSurfaceView
{

	public GLRenderer mRenderer;
	
	public GLSurf(Context context)
    {
        super(context);

        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        // Set the Renderer for drawing on the GLSurfaceView
        this.mRenderer = new GLRenderer(context);
        setRenderer(this.mRenderer);

        // Render the view only when there is a change in the drawing data
        //setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

}
