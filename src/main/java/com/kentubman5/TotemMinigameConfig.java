package com.kentubman5;

import com.kentubman5.overlays.TotemOverlay;
import net.runelite.client.config.*;

import java.awt.*;

@ConfigGroup("totem-minigame")
public interface TotemMinigameConfig extends Config
{
	// Sections
	@ConfigSection(
		name = "Totem",
		description = "Totem Section",
		position = 0
	)
	String SECTION_TOTEM = "totem";

	@ConfigSection(
		name = "Totem Offerings",
		description = "Totem Offerings Section",
		position = 1
	)
	String SECTION_TOTEM_OFFERINGS = "totem-offerings";

	@ConfigSection(
		name = "Spirits",
		description = "Spirits Section",
		position = 2,
		closedByDefault = true
	)
	String SECTION_SPIRITS = "spirits";

	@ConfigSection(
		name = "Ent Trails",
		description = "Ent Trails",
		position = 3,
		closedByDefault = true
	)
	String SECTION_ENT_TRAILS = "ent_trails";

	@ConfigSection(
		name = "Ents",
		description = "Ents",
		position = 4,
		closedByDefault = true
	)
	String SECTION_ENTS = "ents";

	@ConfigSection(
		name = "Minigame Panel",
		description = "Minigame Panel",
		position = 5,
		closedByDefault = true
	)
	String SECTION_MINIGAME_PANEL = "minigame_panel";

	// Totem
	@ConfigItem(
		keyName = "totemNoBaseTileColor",
		name = "Totem (No Base) Color",
		description = "The color of the tile when there is no base on the totem",
		section = SECTION_TOTEM
	)
	@Alpha
	default Color getTotemNoBaseTileColor() { return Color.red; }

	@ConfigItem(
		keyName = "totemNotCarvedTileColor",
		name = "Totem (Not Carved) Color",
		description = "The color of the tile when there is a base on the totem but it's not carved",
		section = SECTION_TOTEM
	)
	@Alpha
	default Color getTotemNotCarvedTileColor() { return Color.orange; }

	@ConfigItem(
		keyName = "totemCarvedTileColor",
		name = "Totem (Carved) Color",
		description = "The color of the tile when there is a carved base on the totem",
		section = SECTION_TOTEM
	)
	@Alpha
	default Color getTotemCarvedTileColor() { return Color.yellow; }

	@ConfigItem(
			keyName = "totemDecoratedColor",
			name = "Totem (Decorated) Color",
			description = "The color of the tile when there is a carved base that is fully decorated on the totem",
			section = SECTION_TOTEM
	)
	@Alpha
	default Color getTotemDecoratedColor() { return Color.green; }

	@ConfigItem(
		keyName = "totemTextType",
		name = "Totem Text Type",
		description = "Decides what will be displayed on the totem, numbers are the number you have to press",
		section = SECTION_TOTEM
	)
	@Alpha
	default TotemOverlay.TextType getTotemTextType() { return TotemOverlay.TextType.ANIMAL_NAME_AND_NUMBER; }

	@ConfigItem(
		keyName = "totemTextColor",
		name = "Totem Text Color",
		description = "The color of the text on the totem",
		section = SECTION_TOTEM
	)
	@Alpha
	default Color getTotemTextColor() { return Color.white; }

	// Totem Offerings
	String CONFIG_ITEM_TOTEM_OFFERINGS_OVERLAY_IS_ENABLED_KEY = "totemOfferingsOverlayIsEnabled";
	@ConfigItem(
		keyName = CONFIG_ITEM_TOTEM_OFFERINGS_OVERLAY_IS_ENABLED_KEY,
		name = "Enable",
		description = "Enable the totem offerings overlay",
		section = SECTION_TOTEM_OFFERINGS
	)
	default boolean getTotemOfferingsOverlayIsEnabled() { return true; }

	@ConfigItem(
		keyName = "totemOfferingsColor",
		name = "Totem Offerings Color",
		description = "The color to highlight the offerings if they are",
		section = SECTION_TOTEM_OFFERINGS
	)
	@Alpha
	default Color getTotemOfferingsColor() { return new Color(0xFFFFC7C7); }

	// Spirits
	String CONFIG_ITEM_SPIRIT_OVERLAY_IS_ENABLED_KEY = "spiritOverlayIsEnabled";
	@ConfigItem(
			keyName = CONFIG_ITEM_SPIRIT_OVERLAY_IS_ENABLED_KEY,
			name = "Enable",
			description = "Enable the spirit overlay",
			section = SECTION_SPIRITS
	)
	default boolean getSpiritOverlayIsEnabled() { return true; }

	@ConfigItem(
		keyName = "spiritHullColor",
		name = "Spirit Hull Color",
		description = "The color of the highlighted hull of the spirits",
		section = SECTION_SPIRITS
	)
	@Alpha
	default Color getSpiritHullColor() { return Color.darkGray; }

	// Ent Trails
	String CONFIG_ITEM_ENT_TRAIL_OVERLAY_IS_ENABLED_KEY = "entTrailOverlayIsEnabled";
	@ConfigItem(
			keyName = CONFIG_ITEM_ENT_TRAIL_OVERLAY_IS_ENABLED_KEY,
			name = "Enable",
			description = "Enable the ent trail overlay",
			section = SECTION_ENT_TRAILS
	)
	default boolean getEntTrailOverlayIsEnabled() { return true; }

	@ConfigItem(
		keyName = "entTrailsColor",
		name = "Ent Trails Color",
		description = "The color for ent trails",
		section = SECTION_ENT_TRAILS
	)
	@Alpha
	default Color getEntTrailsColor() { return Color.gray; }

	// Ent
	String CONFIG_ITEM_ENTS_OVERLAY_IS_ENABLED_KEY = "entsOverlayIsEnabled";
	@ConfigItem(
			keyName = CONFIG_ITEM_ENTS_OVERLAY_IS_ENABLED_KEY,
			name = "Enable",
			description = "Enable the ents overlay",
			section = SECTION_ENTS
	)
	default boolean getEntsOverlayIsEnabled() { return true; }

	@ConfigItem(
		keyName = "entColor",
		name = "Ent Color",
		description = "The color of the ents",
		section = SECTION_ENTS
	)
	@Alpha
	default Color getEntColor() { return new Color(0x800000); }

	// Minigame Panel
	String CONFIG_ITEM_MINIGAME_PANEL_OVERLAY_IS_ENABLED_KEY = "minigamePanelOverlayIsEnabled";
	@ConfigItem(
		keyName = CONFIG_ITEM_MINIGAME_PANEL_OVERLAY_IS_ENABLED_KEY,
		name = "Enable",
		description = "Enable the minigame panel overlay",
		section = SECTION_MINIGAME_PANEL
	)
	default boolean getMinigamePanelOverlayIsEnabled() { return true; }
}
