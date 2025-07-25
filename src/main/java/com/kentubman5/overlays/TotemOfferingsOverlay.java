package com.kentubman5.overlays;

import com.kentubman5.TotemMinigameConfig;
import com.kentubman5.TotemMinigamePlugin;
import com.kentubman5.totem.TotemLocations;
import net.runelite.api.Client;
import net.runelite.api.Perspective;
import net.runelite.api.coords.LocalPoint;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;

import javax.inject.Inject;
import java.awt.*;

public class TotemOfferingsOverlay extends Overlay {
    private final Client client;
    private final TotemMinigameConfig config;
    private final TotemMinigamePlugin plugin;

    @Inject
    public TotemOfferingsOverlay(
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
    public Dimension render(Graphics2D graphics) {
        renderTotemOfferings(graphics);
        return null;
    }

    private void renderTotemOfferings(Graphics2D graphics2D) {
        for (int i = 1; i < TotemLocations.TOTEM_OFFERINGS_MAP.size() + 1; i++) {
            var totemOfferingsLocation = TotemLocations.TOTEM_OFFERINGS_MAP.get(i);
            var totem = this.plugin.totems[i - 1];
            if (totemOfferingsLocation == null) {
                continue;
            }

            // Skip if we don't have any points
            if (totem.points <= 0) {
                continue;
            }


            var totemOfferingsLocalPoint = LocalPoint.fromWorld(client.getTopLevelWorldView(), totemOfferingsLocation);
            if (totemOfferingsLocalPoint == null) {
                continue;
            }

            var polygon = Perspective.getCanvasTilePoly(client, totemOfferingsLocalPoint);
            if (polygon == null) {
                continue;
            }

            var offeringsTileColor = this.config.getTotemOfferingsColor();
            OverlayUtil.renderPolygon(graphics2D, polygon, offeringsTileColor);

            var text = "" + totem.points;
            var textLocation = Perspective.getCanvasTextLocation(client, graphics2D, totemOfferingsLocalPoint, text, 0);
            OverlayUtil.renderTextLocation(graphics2D, textLocation, text, offeringsTileColor);
        }
    }
}
