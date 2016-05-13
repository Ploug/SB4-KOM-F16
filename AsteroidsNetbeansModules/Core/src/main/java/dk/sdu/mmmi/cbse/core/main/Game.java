package dk.sdu.mmmi.cbse.core.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dk.sdu.mmmi.cbse.common.data.Entity;
import static dk.sdu.mmmi.cbse.common.data.EntityType.PLAYER;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.core.managers.GameInputProcessor;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;

public class Game implements ApplicationListener
{

    private static OrthographicCamera cam;
    private ShapeRenderer sr;
    private SpriteBatch batch;
    private BitmapFont font;
    private final Lookup lookup = Lookup.getDefault();
    private final GameData gameData = new GameData();
    private Map<String, Entity> world = new ConcurrentHashMap<>();
    private Set<IGamePluginService> gamePlugins;
    private Lookup.Result<IGamePluginService> result;

    @Override
    public void create()
    {
        gameData.setDisplayWidth(Gdx.graphics.getWidth());
        gameData.setDisplayHeight(Gdx.graphics.getHeight());

        cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        cam.translate(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
        cam.update();

        sr = new ShapeRenderer();
        batch = new SpriteBatch();
        font = new BitmapFont();
        Gdx.input.setInputProcessor(new GameInputProcessor(gameData));

        result = lookup.lookupResult(IGamePluginService.class);
        result.addLookupListener(lookupListener);
        gamePlugins = ConcurrentHashMap.newKeySet();
        gamePlugins.addAll(result.allInstances());
        result.allItems();
        for (IGamePluginService plugin : gamePlugins)
        {
            plugin.start(gameData, world);
        }

    }
    long timeBefore = System.currentTimeMillis();

    @Override
    public void render()
    {
        long before = System.currentTimeMillis();
        // clear screen to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameData.setDelta(Gdx.graphics.getDeltaTime());
        gameData.getKeys().update();
        update();
        draw();
    }

    private void update()
    {
        // Update
        for (IEntityProcessingService entityProcessorService : getEntityProcessingServices())
        {
            for (Entity e : world.values())
            {
                entityProcessorService.process(gameData, world, e);
            }
        }
    }
    int fpsCount = 0;
    double deltaTotal = 0;

    private void draw()
    {
        for (Entity entity : world.values())
        {
            sr.setColor(1, 1, 1, 1);

            sr.begin(ShapeRenderer.ShapeType.Line);
            java.awt.Color color = entity.getColor();
            if (color != null)
            {
                sr.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()));
            }

            float[] shapex = entity.getShapeX();
            float[] shapey = entity.getShapeY();

            for (int i = 0, j = shapex.length - 1;
                    i < shapex.length;
                    j = i++)
            {

                sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);
            }

            sr.end();
            if (entity.getType() == PLAYER)
            {
                batch.begin();
                font.draw(batch, "Life: " + entity.getLife(), 40, 70);
                batch.end();
            }
        }
        if (fpsCount > 19)
        {
            fpsCount = 0;
            deltaTotal = 0;
        }
        fpsCount++;
        deltaTotal += gameData.getDelta();
        batch.begin();
        font.draw(batch, "Fps: " + Math.round(1 / deltaTotal * fpsCount), 40, 100);
        batch.end();
        batch.begin();
        font.draw(batch, "Score: " + gameData.getScore(), 40, 40);
        batch.end();
    }

    @Override
    public void resize(int width, int height)
    {
    }

    @Override
    public void pause()
    {
    }

    @Override
    public void resume()
    {
    }

    @Override
    public void dispose()
    {
    }

    private Collection<? extends IEntityProcessingService> getEntityProcessingServices()
    {
        return lookup.lookupAll(IEntityProcessingService.class);
    }

    private final LookupListener lookupListener = new LookupListener()
    {
        @Override
        public void resultChanged(LookupEvent le)
        {
            Collection<IGamePluginService> updatedPlugins = (Collection<IGamePluginService>)lookup.lookupAll(IGamePluginService.class);
            for (IGamePluginService updatedPlugin : updatedPlugins)
            {
                if (!gamePlugins.contains(updatedPlugin))
                {
                    updatedPlugin.start(gameData, world);
                    gamePlugins.add(updatedPlugin);
                }
            }

            for (IGamePluginService oldPlugin : gamePlugins)
            {
                if (!updatedPlugins.contains(oldPlugin))
                {
                    oldPlugin.stop(gameData);
                    gamePlugins.remove(oldPlugin);
                }
            }
        }
    };
}
