package OpenGL;

/**
 * Created by home on 29/11/2014.
 */
public interface DrawableObject {

    public void move(float x, float y);

    public void draw(float[] mMVPMatrix);

    public boolean isTouched(float x, float y);

}
