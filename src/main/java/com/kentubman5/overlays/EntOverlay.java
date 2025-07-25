package com.kentubman5.overlays;

import com.kentubman5.TotemMinigameConfig;
import com.kentubman5.TotemMinigamePlugin;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;

import javax.inject.Inject;
import java.awt.*;

public class EntOverlay extends Overlay {
    private final Client client;
    private final TotemMinigameConfig config;
    private final TotemMinigamePlugin plugin;

    @Inject
    public EntOverlay(
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
        renderEnts(graphics);
        return null;
    }

    private void renderEnts(final Graphics2D graphics2D) {
        for (final NPC ent : this.plugin.getEnts()) {
            var polygon = ent.getConvexHull();
            if (polygon == null) {
                continue;
            }

            var entColor = this.config.getEntColor();

            OverlayUtil.renderPolygon(graphics2D, polygon, entColor);

            var entName = ent.getName();
            var textLocation = ent.getCanvasTextLocation(graphics2D, entName, 0);
            OverlayUtil.renderTextLocation(graphics2D, textLocation, ent.getName(), entColor);
        }
    }
}
