package com.kentubman5.overlays;

import com.kentubman5.*;
import com.kentubman5.totem.TotemAnimalNumber;
import com.kentubman5.totem.TotemBaseType;
import com.kentubman5.totem.TotemLocations;
import joptsimple.util.KeyValuePair;
import net.runelite.api.Client;
import net.runelite.api.Perspective;
import net.runelite.api.coords.LocalPoint;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;

import javax.inject.Inject;
import java.awt.*;

public class TotemOverlay extends Overlay {
    private final Client client;
    private final TotemMinigameConfig config;
    private final TotemMinigamePlugin plugin;

    public enum TextType {
        NONE("None"),
        ANIMAL_NAME_ONLY("Animal Name Only"),
        ANIMAL_NUMBER_ONLY("Number Only"),
        ANIMAL_NAME_AND_NUMBER("Animal Name and Number");

        private final String name;

        TextType(String name) {
            this.name = name;
        }
    }

    @Inject
    public TotemOverlay(
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
        renderTotemStats(graphics);
        return null;
    }

    public Color getTotemTileColor(int totemId) {
        var totem = this.plugin.totems[totemId - 1];
        if (totem == null) {
            return Color.black;
        }
        if (totem.base == TotemBaseType.NONE) {
            return this.config.getTotemNoBaseTileColor();
        }

        if (totem.decorations == 4) {
            return this.config.getTotemDecoratedColor();
        }

        if (totem.baseCarved == 1) {
            return this.config.getTotemCarvedTileColor();
        }

        return this.config.getTotemNotCarvedTileColor();
    }

    private void renderTotemStats(Graphics2D graphics2D) {
        // Get the closest totem so we know when to render text
//        var playerWorldLocation = this.client.getLocalPlayer().getWorldLocation();
//
//        var minDistanceToPlayer = 0;
//        var minTotemId = 0;
//        for (int i = 1; i < TotemLocations.TOTEM_MAP.size() + 1; i++) {
//            var totemWorldLocation = TotemLocations.TOTEM_MAP.get(i);
//            var distanceToTotem = playerWorldLocation.distanceTo(totemWorldLocation);
//            if (distanceToTotem < minDistanceToPlayer) {
//                minDistanceToPlayer = distanceToTotem;
//                minTotemId = i;
//            }
//        }

        for (int i = 1; i < TotemLocations.TOTEM_MAP.size() + 1; i++) {
            var totemLocation = TotemLocations.TOTEM_MAP.get(i);
            var totem = this.plugin.totems[i - 1];
            if (totemLocation == null) {
                continue;
            }

            var totemLocalPoint = LocalPoint.fromWorld(client, totemLocation);
            if (totemLocalPoint == null) {
                continue;
            }

            var polygon = Perspective.getCanvasTilePoly(client, totemLocalPoint);
            if (polygon == null) {
                continue;
            }

            var totemTileColor = getTotemTileColor(i);
            OverlayUtil.renderPolygon(graphics2D, polygon, totemTileColor);

            var totemTextBuilder = new StringBuilder("Totem " + i);

            var totemTextType = this.config.getTotemTextType();
            
            if (totemTextType == TextType.ANIMAL_NAME_ONLY) {
                totemTextBuilder.append("\n");
                totemTextBuilder.append(TotemAnimalNumber.friendlyString(totem.animal1, false));
                totemTextBuilder.append(" ");
                totemTextBuilder.append(TotemAnimalNumber.friendlyString(totem.animal2, false));
                totemTextBuilder.append(" ");
                totemTextBuilder.append(TotemAnimalNumber.friendlyString(totem.animal3, false));
            }
            else if (totemTextType == TextType.ANIMAL_NUMBER_ONLY) {
                totemTextBuilder.append("\n");
                totemTextBuilder.append(totem.animal1);
                totemTextBuilder.append(" ");
                totemTextBuilder.append(totem.animal2);
                totemTextBuilder.append(" ");
                totemTextBuilder.append(totem.animal3);
            }
            else if (totemTextType == TextType.ANIMAL_NAME_AND_NUMBER) {
                totemTextBuilder.append("\n");
                totemTextBuilder.append(TotemAnimalNumber.friendlyString(totem.animal1, true));
                totemTextBuilder.append(" ");
                totemTextBuilder.append(TotemAnimalNumber.friendlyString(totem.animal2, true));
                totemTextBuilder.append(" ");
                totemTextBuilder.append(TotemAnimalNumber.friendlyString(totem.animal3, true));
            }
            var totemText = totemTextBuilder.toString();

            MultilineOverlayTextUtil.renderMultilineText(client, graphics2D, totemLocalPoint, totemText, this.config.getTotemTextColor(), 100);
        }
    }
}
