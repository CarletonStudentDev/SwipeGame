package carletonstudentdev.googleplayservices;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;


public class MainActivity extends Activity
{

    public GLSurf glSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


        int result = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());

        if ( result != ConnectionResult.SUCCESS)
            GooglePlayServicesUtil.getErrorDialog(result, this, 1).show();


        // We create our Surfaceview for our OpenGL here.
        this.glSurfaceView = new GLSurf(this);

        View view = View.inflate(getApplicationContext(), R.layout.activity_main, null);

        // Retrieve our Relative layout from our main layout we just set to our view.
        RelativeLayout layout = new RelativeLayout(getApplicationContext());

        // Attach our surfaceview to our relative layout from our main layout.
        RelativeLayout.LayoutParams glParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        layout.addView(this.glSurfaceView, glParams);
        layout.addView(view);
        setContentView(layout);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
            return true;

        return super.onOptionsItemSelected(item);
    }
}
