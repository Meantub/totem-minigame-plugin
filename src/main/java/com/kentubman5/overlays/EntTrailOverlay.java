package com.kentubman5.overlays;

import com.kentubman5.TotemMinigameConfig;
import com.kentubman5.TotemMinigamePlugin;
import net.runelite.api.Client;
import net.runelite.api.gameval.ObjectID;
import net.runelite.client.ui.overlay.*;

import javax.inject.Inject;
import java.awt.*;

public class EntTrailOverlay extends Overlay {
    private final Client client;
    private final TotemMinigameConfig config;
    private final TotemMinigamePlugin plugin;

    private final int MAX_DISTANCE = 2350;

    @Inject
    public EntTrailOverlay(
            final Client client,
            final TotemMinigameConfig config,
            final TotemMinigamePlugin plugin
    )
    {
        super(plugin);
        this.client = client;
        this.config = config;
        this.plugin = plugin;

        setPosition(OverlayPosition.DYNAMIC);
        setPriority(PRIORITY_HIGH);
        setLayer(OverlayLayer.ABOVE_SCENE);
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        renderEntTrail(graphics);
        return null;
    }

    public void renderEntTrail(Graphics2D graphics2D) {
        var playerLocation = client.getLocalPlayer().getLocalLocation();

        for (var entTotemTrailPart : this.plugin.entTrailGameObjects) {
            // Skip if is not on same plane
            if (entTotemTrailPart.getPlane() != client.getTopLevelWorldView().getPlane()) {
                continue;
            }
            // Skip if too far
            if (entTotemTrailPart.getLocalLocation().distanceTo(playerLocation) > MAX_DISTANCE) {
                continue;
            }

            var polygon = entTotemTrailPart.getCanvasTilePoly();
            if (polygon == null) {
                continue;
            }

            // TODO: On the Ent Trail GameObject there is something with an A separate from the
            // ID that is 12345 and when you step on it, the object becomes 12346, if I can figure
            // out what that is then we can show red for untriggered ones

            // TODO: Separate we probably also only want to show red if we don't already have a trail buff active
            // for whatever buff this GameObject is associated with, it might semi-difficult for us to figure out
            // what it's associated with

            OverlayUtil.renderPolygon(graphics2D, polygon, this.config.getEntTrailsColor());
        }
    }
}
