package com.kentubman5.overlays;


import com.kentubman5.TotemMinigameConfig;
import com.kentubman5.TotemMinigamePlugin;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.client.ui.overlay.*;

import javax.inject.Inject;
import java.awt.*;

public class SpiritOverlay extends Overlay {
    private final Client client;
    private final TotemMinigameConfig config;
    private final TotemMinigamePlugin plugin;

    @Inject
    public SpiritOverlay(
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
        setLayer(OverlayLayer.UNDER_WIDGETS);
    }

    @Override
    public Dimension render(Graphics2D graphics2D) {
        renderSpirits(graphics2D);
        return null;
    }

    private void renderSpirits(final Graphics2D graphics2D) {
        for (final NPC spirit : this.plugin.getSpirits()) {
            var polygon = spirit.getConvexHull();
            if (polygon == null) {
                continue;
            }

            var spiritColor = this.config.getSpiritHullColor();

            OverlayUtil.renderPolygon(graphics2D, polygon, spiritColor);

            var spiritName = spirit.getName();
            var textLocation = spirit.getCanvasTextLocation(graphics2D, spiritName, 0);
            if (textLocation == null) {
                continue;
            }
            OverlayUtil.renderTextLocation(graphics2D, textLocation, spirit.getName(), spiritColor);
        }
    }
}
