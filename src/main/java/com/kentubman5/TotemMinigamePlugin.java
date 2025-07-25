package com.kentubman5;

import com.google.common.collect.ImmutableSet;
import com.google.inject.Provides;
import javax.inject.Inject;

import com.kentubman5.overlays.*;
import com.kentubman5.totem.Totem;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.*;
import net.runelite.api.gameval.ObjectID;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.gpu.GpuPlugin;
import net.runelite.client.plugins.gpu.GpuPluginConfig;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

@Slf4j
@PluginDescriptor(
	name = "Totem Minigame"
)
public class TotemMinigamePlugin extends Plugin
{
	private static final Pattern OFFERINGS_COLLECTED_REGEX = Pattern.compile(
		"You collect (\\d+) offerings"
	);
	public int ENT_ID = 14634;

	public int BUFFALO_SPIRIT_ID = 14589;
	public int JAGUAR_SPIRIT_ID = 14590;
	public int EAGLE_SPIRIT_ID = 14591;
	public int SNAKE_SPIRIT_ID = 14592;
	public int SCORPION_SPIRIT_ID = 14593;

	private static ImmutableSet<Integer> AUBURN_VALLEY_REGION_IDS = ImmutableSet
			.of(5427, 5428, 5683, 5684);

	@Inject
	private Client client;

	@Inject
	private TotemMinigameConfig config;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private SpiritOverlay spiritOverlay;

	@Inject
	private TotemOverlay totemOverlay;

	@Inject
	private EntTrailOverlay entTrailOverlay;

	@Inject
	private TotemOfferingsOverlay totemOfferingsOverlay;

	@Inject
	private EntOverlay entOverlay;

	@Inject
	private PointsOverlayPanel pointsOverlayPanel;

	@Getter
	private final List<NPC> spirits = new ArrayList<>();

	@Getter
	private final List<NPC> ents = new ArrayList<>();

	@Getter
	private int offeringsCollected = 0;

	@Getter(AccessLevel.PACKAGE)
	public final Totem[] totems = new Totem[8];
	private boolean hasTotemStateChanged = false;

	@Getter(AccessLevel.PACKAGE)
	public final List<GameObject> entTrailGameObjects = new ArrayList<>();

	public boolean isInAuburnValley = false;

	private final ImmutableSet<Integer> ENT_TRAILS_GAME_OBJECT_IDS = ImmutableSet
			.of(net.runelite.api.gameval.ObjectID.ENT_TOTEMS_TRAIL_PART_0, ObjectID.ENT_TOTEMS_TRAIL_PART_1);

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(spiritOverlay);
		overlayManager.add(totemOverlay);
		overlayManager.add(entTrailOverlay);
		overlayManager.add(totemOfferingsOverlay);
		overlayManager.add(entOverlay);
		overlayManager.add(pointsOverlayPanel);
		for (int i = 0; i < totems.length; i++)
		{
			totems[i] = new Totem(i + 1);
		}
		log.info("Totem minigame started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(spiritOverlay);
		overlayManager.remove(totemOverlay);
		overlayManager.remove(entTrailOverlay);
		overlayManager.remove(totemOfferingsOverlay);
		overlayManager.remove(entOverlay);
		overlayManager.remove(pointsOverlayPanel);
		log.info("Totem minigame stopped!");
	}

	@Subscribe
	public void onGameObjectSpawned(GameObjectSpawned event)
	{
		var gameObject = event.getGameObject();

		if (ENT_TRAILS_GAME_OBJECT_IDS.contains(gameObject.getId()))
		{
			entTrailGameObjects.add(gameObject);
		}
	}

	@Subscribe
	public void onGameObjectDespawned(GameObjectDespawned event)
	{
		var gameObject = event.getGameObject();

		entTrailGameObjects.remove(gameObject);
	}

	@Subscribe
	public void onVarbitChanged(VarbitChanged varbitChanged)
	{
		var varbitId = varbitChanged.getVarbitId();

		var totemSite = Totem.identifySiteFromVarbit(varbitId);
		if (totemSite == -1) {
			return;
		}

		var varbitValue = varbitChanged.getValue();

		totems[totemSite].setBasedOnVarbit(varbitId, varbitValue);
		if (TrailBuffVarbits.isTrailBuffActiveVarbit(varbitId)) {
			totems[totemSite].isTrailBuffActive = varbitValue == 1;
		}
		hasTotemStateChanged = true;
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged event)
	{
		if (event.getGameState() == GameState.LOADING) {
			entTrailGameObjects.clear();

			isInAuburnValley = isInAuburnValley();
		}
		else if (event.getGameState() == GameState.LOGIN_SCREEN) {
			isInAuburnValley = false;
		}
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged configChanged)
	{
		if (!isInAuburnValley) return;
		onConfigOverlayEnabledOrDisabled(configChanged, TotemMinigameConfig.CONFIG_ITEM_TOTEM_OFFERINGS_OVERLAY_IS_ENABLED_KEY, totemOfferingsOverlay);
		onConfigOverlayEnabledOrDisabled(configChanged, TotemMinigameConfig.CONFIG_ITEM_SPIRIT_OVERLAY_IS_ENABLED_KEY, spiritOverlay);
		onConfigOverlayEnabledOrDisabled(configChanged, TotemMinigameConfig.CONFIG_ITEM_ENT_TRAIL_OVERLAY_IS_ENABLED_KEY, entTrailOverlay);
		onConfigOverlayEnabledOrDisabled(configChanged, TotemMinigameConfig.CONFIG_ITEM_ENTS_OVERLAY_IS_ENABLED_KEY, entOverlay);
		onConfigOverlayEnabledOrDisabled(configChanged, TotemMinigameConfig.CONFIG_ITEM_MINIGAME_PANEL_OVERLAY_IS_ENABLED_KEY, pointsOverlayPanel);
	}

	public void onConfigOverlayEnabledOrDisabled(ConfigChanged configChanged, String key, Overlay overlay)
	{
		if (configChanged.getKey().equals(key)) {
			if (configChanged.getNewValue() != null && configChanged.getNewValue().equals("true")) {
				overlayManager.add(overlay);
			}
			else {
				overlayManager.remove(overlay);
			}
		}
	}

	@Subscribe
	public void onGameTick(GameTick gameTick) {
		if (hasTotemStateChanged) {
			log.info("Totem Minigame state changed!");
			for (int totemId = 0; totemId < totems.length; totemId++) {
				log.info("Totem {}: {}", totemId + 1, totems[totemId]);
			}
			hasTotemStateChanged = false;
		}
	}

	@Subscribe
	public void onNpcSpawned(NpcSpawned npcSpawned)
	{
		var npc = npcSpawned.getNpc();

		if (isSpirit(npc))
		{
			spirits.add(npc);
		}
		if (npc.getId() == ENT_ID) {
			ents.add(npc);
		}
	}

	@Subscribe
	public void onChatMessage(ChatMessage event)
	{
		if (event.getType() != ChatMessageType.GAMEMESSAGE) return;

		var message = event.getMessage();

		var offeringsCollectedMatcher = OFFERINGS_COLLECTED_REGEX.matcher(message);
		if (offeringsCollectedMatcher.find()) {
			offeringsCollected += Integer.parseInt(offeringsCollectedMatcher.group(1));
		}
	}

	@Subscribe
	public void onNpcChanged(NpcChanged npcChanged)
	{
		var npc = npcChanged.getNpc();
		log.warn("An NPC has changed! {} {}", npc.getName(), npc.getId());

		if (isSpirit(npc))
		{
			log.warn("A spirit NPC has changed! {}", npc);
		}
	}

	@Subscribe
	public void onNpcDespawned(NpcDespawned npcDespawned)
	{
		var npc = npcDespawned.getNpc();

		if (isSpirit(npc))
		{
			spirits.removeIf(n -> n == npc);
		}
		if (npc.getId() == ENT_ID) {
			ents.remove(npc);
		}
	}

	private boolean isInAuburnValley()
	{
		var gameState = client.getGameState();
		if (gameState != GameState.LOGGED_IN && gameState != GameState.LOADING) {
			return false;
		}

		int[] currentMapRegions = client.getTopLevelWorldView().getMapRegions();

		for (int region : currentMapRegions) {
			if (AUBURN_VALLEY_REGION_IDS.contains(region)) {
				return true;
			}
		}
		return false;
	}

	public boolean isSpirit(NPC npc) {
		var npcId = npc.getId();

		return npcId == SCORPION_SPIRIT_ID || npcId == JAGUAR_SPIRIT_ID || npcId == BUFFALO_SPIRIT_ID
				|| npcId == EAGLE_SPIRIT_ID || npcId == SNAKE_SPIRIT_ID;
	}

	@Provides
	TotemMinigameConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(TotemMinigameConfig.class);
	}
}
