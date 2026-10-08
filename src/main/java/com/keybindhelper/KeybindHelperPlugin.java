package com.keybindhelper;

import com.google.inject.Provides;
import javax.inject.Inject;
import net.runelite.api.Actor;
import net.runelite.api.Client;
import net.runelite.api.Player;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.HitsplatApplied;
import net.runelite.api.events.InteractingChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@PluginDescriptor(
	name = "Keybind Helper Overlay",
	description = "Shows keybind overlay labels over side-panel tab icons.",
	tags = {"keybind", "overlay", "fkey", "hotkey", "tabs", "pvp", "pk"}
)
public class KeybindHelperPlugin extends Plugin
{
	private static final long GAME_TICK_MILLIS = 600L;

	@Inject
	private Client client;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private KeybindHelperOverlay overlay;

	@Inject
	private KeybindHelperConfig config;

	private int combatTicksRemaining;
	private long combatHideUntilMillis;

	@Override
	protected void startUp()
	{
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown()
	{
		overlayManager.remove(overlay);
		clearCombatHideDelay();
	}

	boolean isInCombat()
	{
		return isActivelyInCombat() || getCombatHideMillisRemaining() > 0;
	}

	boolean isActivelyInCombat()
	{
		Player localPlayer = client.getLocalPlayer();
		return localPlayer != null && localPlayer.getInteracting() != null;
	}

	long getCombatHideMillisRemaining()
	{
		return Math.max(0L, combatHideUntilMillis - System.currentTimeMillis());
	}

	@Subscribe
	public void onInteractingChanged(InteractingChanged event)
	{
		Player localPlayer = client.getLocalPlayer();
		if (localPlayer == null)
		{
			return;
		}

		Actor source = event.getSource();
		Actor target = event.getTarget();
		if ((source == localPlayer && target != null) || target == localPlayer)
		{
			resetCombatHideDelay();
		}
	}

	@Subscribe
	public void onHitsplatApplied(HitsplatApplied event)
	{
		if (event.getActor() == client.getLocalPlayer())
		{
			resetCombatHideDelay();
		}
	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
		if (isActivelyInCombat())
		{
			resetCombatHideDelay();
			return;
		}

		if (combatTicksRemaining > 0)
		{
			combatTicksRemaining--;
		}
		else
		{
			combatHideUntilMillis = 0;
		}
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged event)
	{
		clearCombatHideDelay();
	}

	@Provides
	KeybindHelperConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(KeybindHelperConfig.class);
	}

	private void resetCombatHideDelay()
	{
		int hideDelayTicks = config.combatHideDelay();
		combatTicksRemaining = hideDelayTicks;
		combatHideUntilMillis = hideDelayTicks <= 0 ? 0 : System.currentTimeMillis() + hideDelayTicks * GAME_TICK_MILLIS;
	}

	private void clearCombatHideDelay()
	{
		combatTicksRemaining = 0;
		combatHideUntilMillis = 0;
	}
}
