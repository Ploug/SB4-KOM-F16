package dk.sdu.mmmi.cbse.core.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dk.sdu.mmmi.cbse.core.managers.GameInputProcessor;
import dk.sdu.mmmi.cbse.osgicommon.data.Entity;
import dk.sdu.mmmi.cbse.osgicommon.data.GameData;
import dk.sdu.mmmi.cbse.osgicommon.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.osgicommon.services.IGamePluginService;
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

    private void draw()
    {
        for (Entity entity : world.values())
        {
            sr.setColor(1, 1, 1, 1);

            sr.begin(ShapeRenderer.ShapeType.Line);

            float[] shapex = entity.getShapeX();
            float[] shapey = entity.getShapeY();

            for (int i = 0, j = shapex.length - 1;
                    i < shapex.length;
                    j = i++)
            {

                sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);
            }

            sr.end();
        }
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
