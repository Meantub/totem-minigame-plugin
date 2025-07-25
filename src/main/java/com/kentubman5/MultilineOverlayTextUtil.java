package com.kentubman5;

import lombok.experimental.UtilityClass;
import net.runelite.api.Client;
import net.runelite.api.Perspective;
import net.runelite.api.coords.LocalPoint;
import net.runelite.client.ui.overlay.OverlayUtil;

import java.awt.*;

@UtilityClass
public class MultilineOverlayTextUtil {
    public static void renderMultilineText(Client client, Graphics2D graphics, LocalPoint localLocation, String text, Color color, int zOffset)
    {
        // Split the string by \n
        var lines = text.split("\n");

        for (int i = 0; i < lines.length; i++) {
            var line = lines[i];

            var txtLoc = Perspective.getCanvasTextLocation(client, graphics, localLocation, line, zOffset);

            if (txtLoc == null) continue;

            var fm = graphics.getFontMetrics();
            var bounds = fm.getStringBounds(line, graphics);
            var yLineOffset = (int) (i * bounds.getY());
            var xLineOffset = 0; //(int) ((i * bounds.getWidth()) / 2);

            var lineTxtLoc = new net.runelite.api.Point(txtLoc.getX() - xLineOffset, txtLoc.getY() - yLineOffset);
            OverlayUtil.renderTextLocation(graphics, lineTxtLoc, line, color);
        }
    }

    public static void renderMultilineText(Client client, Graphics2D graphics, LocalPoint localLocation, String text, Color color)
    {
        renderMultilineText(client, graphics, localLocation, text, color, 0);
    }
}
