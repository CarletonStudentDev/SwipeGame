package AndroidServices;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.example.owner.gameproject.MyActivity;


/**
 * MediaSounds loads and plays all the required sounds
 * for the app.
 *
 * @author Jeton Sinoimeri
 * @version 1.0
 * @since 2015-02-18
 *
 */

public class MediaSounds
{

    /**
     * sounds: SoundPool instance representing the audio resources
     *         of the app.
     *
     */

    private static SoundPool sounds;


    /**
     * Initializes the Sound Pool instance depending on the OS version.
     *
     */

    public static void initializeSoundPool()
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
        {
            SoundPool.Builder builder =  new SoundPool.Builder();
            builder.setMaxStreams(10); //Assuming only 10 sounds will play simultaneously
            sounds = builder.build(); // Builder.build returns a new instance of SoundPool.
        }

        else
            sounds = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
    }


    /**
     * Loads and Plays the corresponding sound.
     *
     * @param context: Context instance representing the context of the app.
     * @param soundFile: integer value representing the sound file to be
     *                   loaded and played.
     * @param priority: integer value representing the priority of the sound.
     * @param speed: float value representing the speed of which the sound
     *               should be played at.
     *
     */

    public static void loadPlaySound(Context context, int soundFile, int priority, float speed)
    {
        final int soundId = sounds.load(context, soundFile, priority);

        final float playSpeed = speed,
                    volume = MyActivity.getVolume();

        // wait until sound has been loaded before playing it
        sounds.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener()
        {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status)
            {
                sounds.play(soundId, volume, volume, 0, 0, playSpeed);
            }
        });
    }
}
