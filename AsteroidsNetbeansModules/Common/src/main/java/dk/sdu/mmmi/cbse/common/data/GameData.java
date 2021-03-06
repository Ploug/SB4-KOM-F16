package dk.sdu.mmmi.cbse.common.data;

import dk.sdu.mmmi.cbse.common.events.Event;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameData
{
    private float delta;
    private int displayWidth;
    private int displayHeight;
    private final GameKeys keys = new GameKeys();
    private List<Event> events = new CopyOnWriteArrayList();
    private int score = 0;

    public void increaseScore(int increase)
    {
        this.score += increase;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public int getScore()
    {
        return this.score;
    }

    public void addEvent(Event e)
    {
        events.add(e);
    }

    public void removeEvent(Event e)
    {
        events.remove(e);
    }

    public List<Event> getEvents()
    {
        return events;
    }

    public GameKeys getKeys()
    {
        return keys;
    }

    public void setDelta(float delta)
    {
        this.delta = delta;
    }

    public float getDelta()
    {
        return delta;
    }

    public void setDisplayWidth(int width)
    {
        this.displayWidth = width;
    }

    public int getDisplayWidth()
    {
        return displayWidth;
    }

    public void setDisplayHeight(int height)
    {
        this.displayHeight = height;
    }

    public int getDisplayHeight()
    {
        return displayHeight;
    }

}
