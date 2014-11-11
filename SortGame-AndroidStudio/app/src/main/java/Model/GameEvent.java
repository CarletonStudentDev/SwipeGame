package Model;

import java.util.EventObject;


/**
 * GameEvent is an EventObject.
 *
 * @see java.util.EventObject
 *
 * @author Jeton Sinoimeri
 * @version 1.1
 * @since 2014-11-10
 *
 */

public class GameEvent extends EventObject
{

    /**
     * Constructor for the GameEvent class
     *
     * @param arg0 -> Object representing the source of
     *                the Event
     */

    public GameEvent(Object arg0)
    {
        super(arg0);
    }

}
