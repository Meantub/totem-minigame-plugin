package com.kentubman5.overlays;

import com.kentubman5.TotemMinigameConfig;
import com.kentubman5.TotemMinigamePlugin;
import net.runelite.api.Client;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.TextComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

import javax.inject.Inject;
import java.awt.*;

public class PointsOverlayPanel extends OverlayPanel {
    private final Client client;
    private final TotemMinigameConfig config;
    private final TotemMinigamePlugin plugin;

    @Inject
    public PointsOverlayPanel(
        final Client client,
        final TotemMinigameConfig config,
        final TotemMinigamePlugin plugin
    ) {
        super(plugin);
        this.client = client;
        this.config = config;
        this.plugin = plugin;
        setPosition(OverlayPosition.TOP_LEFT);
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        if (!this.plugin.isInAuburnValley) {
            return super.render(graphics);
        }
        panelComponent.getChildren().add(TitleComponent.builder()
                .text("Totem Minigame")
                .build());

        panelComponent.getChildren().add(LineComponent.builder()
                .left("Points earned:")
                .right(Integer.toString(this.plugin.getOfferingsCollected()))
                .build());

        return super.render(graphics);
    }

}
