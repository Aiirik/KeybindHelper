package com.keybindhelper;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.gameval.InterfaceID;
import net.runelite.api.gameval.VarClientID;
import net.runelite.api.gameval.VarbitID;
import net.runelite.api.widgets.Widget;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.config.ModifierlessKeybind;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

class KeybindHelperOverlay extends Overlay
{
	private static final String KEY_REMAPPING_GROUP = "keyremapping";
	private static final String FKEY_REMAP_KEY = "fkeyRemap";
	private static final int SIDE_PANEL_COUNT = 14;
	private static final int LABEL_PADDING_X = 4;
	private static final int LABEL_PADDING_Y = 2;
	private static final long FADE_DURATION_MILLIS = 220L;

	private static final int[][] TAB_KEY_VARBITS =
	{
		{VarbitID.STONE_COMBAT_KEY},
		{VarbitID.STONE_STATS_KEY},
		{VarbitID.STONE_JOURNAL_KEY},
		{VarbitID.STONE_INV_KEY},
		{VarbitID.STONE_WORN_KEY},
		{VarbitID.STONE_PRAYER_KEY},
		{VarbitID.STONE_MAGIC_KEY},
		{VarbitID.STONE_CLANCHAT_KEY},
		{VarbitID.STONE_FRIENDS_KEY},
		{VarbitID.STONE_ACCOUNT_KEY},
		{VarbitID.STONE_LOGOUT_KEY_DESKTOP, VarbitID.STONE_LOGOUT_KEY},
		{VarbitID.STONE_OPTIONS1_KEY},
		{VarbitID.STONE_OPTIONS2_KEY},
		{VarbitID.STONE_MUSIC_KEY}
	};

	private static final int[][] TAB_WIDGET_CANDIDATES =
	{
		{
			InterfaceID.Toplevel.STONE0,
			InterfaceID.ToplevelOsrsStretch.STONE0,
			InterfaceID.ToplevelPreEoc.STONE0,
			InterfaceID.ToplevelOsm.STONE0,
			InterfaceID.Toplevel.ICON0,
			InterfaceID.ToplevelOsrsStretch.ICON0,
			InterfaceID.ToplevelPreEoc.ICON0,
			InterfaceID.ToplevelOsm.ICON0
		},
		{
			InterfaceID.Toplevel.STONE1,
			InterfaceID.ToplevelOsrsStretch.STONE1,
			InterfaceID.ToplevelPreEoc.STONE1,
			InterfaceID.ToplevelOsm.STONE1,
			InterfaceID.ToplevelOsrsStretch.ICON1,
			InterfaceID.Toplevel.ICON1,
			InterfaceID.ToplevelPreEoc.ICON1,
			InterfaceID.ToplevelOsm.ICON1
		},
		{
			InterfaceID.Toplevel.STONE2,
			InterfaceID.ToplevelOsrsStretch.STONE2,
			InterfaceID.ToplevelPreEoc.STONE2,
			InterfaceID.ToplevelOsm.STONE2,
			InterfaceID.Toplevel.ICON2,
			InterfaceID.ToplevelOsrsStretch.ICON2,
			InterfaceID.ToplevelPreEoc.ICON2,
			InterfaceID.ToplevelOsm.ICON2
		},
		{
			InterfaceID.Toplevel.STONE3,
			InterfaceID.ToplevelOsrsStretch.STONE3,
			InterfaceID.ToplevelPreEoc.STONE3,
			InterfaceID.ToplevelOsm.STONE3,
			InterfaceID.Toplevel.ICON3,
			InterfaceID.ToplevelOsrsStretch.ICON3,
			InterfaceID.ToplevelPreEoc.ICON3,
			InterfaceID.ToplevelOsm.ICON3
		},
		{
			InterfaceID.Toplevel.STONE4,
			InterfaceID.ToplevelOsrsStretch.STONE4,
			InterfaceID.ToplevelPreEoc.STONE4,
			InterfaceID.ToplevelOsm.STONE4,
			InterfaceID.Toplevel.ICON4,
			InterfaceID.ToplevelOsrsStretch.ICON4,
			InterfaceID.ToplevelPreEoc.ICON4,
			InterfaceID.ToplevelOsm.ICON4
		},
		{
			InterfaceID.Toplevel.STONE5,
			InterfaceID.ToplevelOsrsStretch.STONE5,
			InterfaceID.ToplevelPreEoc.STONE5,
			InterfaceID.ToplevelOsm.STONE5,
			InterfaceID.Toplevel.ICON5,
			InterfaceID.ToplevelOsrsStretch.ICON5,
			InterfaceID.ToplevelPreEoc.ICON5,
			InterfaceID.ToplevelOsm.ICON5
		},
		{
			InterfaceID.Toplevel.STONE6,
			InterfaceID.ToplevelOsrsStretch.STONE6,
			InterfaceID.ToplevelPreEoc.STONE6,
			InterfaceID.ToplevelOsm.STONE6,
			InterfaceID.Toplevel.ICON6,
			InterfaceID.ToplevelOsrsStretch.ICON6,
			InterfaceID.ToplevelPreEoc.ICON6,
			InterfaceID.ToplevelOsm.ICON6
		},
		{
			InterfaceID.Toplevel.STONE7,
			InterfaceID.ToplevelOsrsStretch.STONE7,
			InterfaceID.ToplevelPreEoc.STONE7,
			InterfaceID.ToplevelOsm.STONE7,
			InterfaceID.Toplevel.ICON7,
			InterfaceID.ToplevelOsrsStretch.ICON7,
			InterfaceID.ToplevelPreEoc.ICON7,
			InterfaceID.ToplevelOsm.ICON7
		},
		{
			InterfaceID.Toplevel.STONE8,
			InterfaceID.ToplevelOsrsStretch.STONE8,
			InterfaceID.ToplevelPreEoc.STONE8,
			InterfaceID.ToplevelOsm.STONE8,
			InterfaceID.Toplevel.ICON8,
			InterfaceID.ToplevelOsrsStretch.ICON8,
			InterfaceID.ToplevelPreEoc.ICON8,
			InterfaceID.ToplevelOsm.ICON8
		},
		{
			InterfaceID.Toplevel.STONE9,
			InterfaceID.ToplevelOsrsStretch.STONE9,
			InterfaceID.ToplevelPreEoc.STONE9,
			InterfaceID.ToplevelOsm.STONE9,
			InterfaceID.Toplevel.ICON9,
			InterfaceID.ToplevelOsrsStretch.ICON9,
			InterfaceID.ToplevelPreEoc.ICON9,
			InterfaceID.ToplevelOsm.ICON9
		},
		{
			InterfaceID.Toplevel.STONE10,
			InterfaceID.ToplevelOsrsStretch.STONE10,
			InterfaceID.ToplevelPreEoc.STONE10,
			InterfaceID.ToplevelOsm.STONE10,
			InterfaceID.Toplevel.ICON10,
			InterfaceID.ToplevelOsrsStretch.ICON10,
			InterfaceID.ToplevelPreEoc.ICON10,
			InterfaceID.ToplevelOsm.ICON10
		},
		{
			InterfaceID.Toplevel.STONE11,
			InterfaceID.ToplevelOsrsStretch.STONE11,
			InterfaceID.ToplevelPreEoc.STONE11,
			InterfaceID.ToplevelOsm.STONE11,
			InterfaceID.Toplevel.ICON11,
			InterfaceID.ToplevelOsrsStretch.ICON11,
			InterfaceID.ToplevelPreEoc.ICON11,
			InterfaceID.ToplevelOsm.ICON11
		},
		{
			InterfaceID.Toplevel.STONE12,
			InterfaceID.ToplevelOsrsStretch.STONE12,
			InterfaceID.ToplevelPreEoc.STONE12,
			InterfaceID.ToplevelOsm.STONE12,
			InterfaceID.Toplevel.ICON12,
			InterfaceID.ToplevelOsrsStretch.ICON12,
			InterfaceID.ToplevelPreEoc.ICON12,
			InterfaceID.ToplevelOsm.ICON12
		},
		{
			InterfaceID.Toplevel.STONE13,
			InterfaceID.ToplevelOsrsStretch.STONE13,
			InterfaceID.ToplevelPreEoc.STONE13,
			InterfaceID.ToplevelOsm.STONE13,
			InterfaceID.Toplevel.ICON13,
			InterfaceID.ToplevelOsrsStretch.ICON13,
			InterfaceID.ToplevelPreEoc.ICON13,
			InterfaceID.ToplevelOsm.ICON13
		}
	};

	@Inject
	private Client client;

	@Inject
	private ConfigManager configManager;

	@Inject
	private KeybindHelperConfig config;

	@Inject
	private KeybindHelperPlugin plugin;

	private boolean wasShowing;
	private long fadeStartedAtMillis;

	@Inject
	KeybindHelperOverlay()
	{
		setPosition(OverlayPosition.DYNAMIC);
		setLayer(OverlayLayer.ALWAYS_ON_TOP);
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (client.getGameState() != GameState.LOGGED_IN)
		{
			clearFadeState();
			return null;
		}

		boolean shouldShow = !config.onlyShowInCombat() || plugin.isInCombat();
		if (!shouldShow && (!config.fadeOutAfterCombat() || !wasShowing))
		{
			clearFadeState();
			return null;
		}

		float overlayAlpha = getOverlayAlpha(shouldShow);
		if (overlayAlpha <= 0.0f)
		{
			clearFadeState();
			return null;
		}

		Composite originalComposite = graphics.getComposite();
		if (overlayAlpha < 1.0f)
		{
			graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, overlayAlpha));
		}

		int openTab = getOpenSidePanelTab();
		Label[] labels = getSidePanelLabels();
		for (int sidePanel = 0; sidePanel < SIDE_PANEL_COUNT; sidePanel++)
		{
			if (config.hideOpenTabLabel() && sidePanel == openTab)
			{
				continue;
			}

			Label label = labels[sidePanel];
			if (label == null || label.text == null)
			{
				continue;
			}

			Widget widget = getTabWidget(sidePanel);
			if (widget == null)
			{
				continue;
			}

			renderLabel(graphics, widget.getBounds(), label.text, sidePanel);
		}

		if (overlayAlpha < 1.0f)
		{
			graphics.setComposite(originalComposite);
		}

		return null;
	}

	private Label[] getSidePanelLabels()
	{
		Label[] labels = new Label[SIDE_PANEL_COUNT];
		for (int visibleTab = 0; visibleTab < SIDE_PANEL_COUNT; visibleTab++)
		{
			if (!isTabEnabled(visibleTab))
			{
				continue;
			}

			int keyCode = getTabKeyCode(visibleTab);
			if (keyCode == 0)
			{
				continue;
			}

			String label = getDisplayLabel(keyCode);
			if (label != null)
			{
				labels[visibleTab] = new Label(label, keyCode);
			}
		}

		return labels;
	}

	private boolean isTabEnabled(int tab)
	{
		switch (tab)
		{
			case 0:
				return config.showCombat();
			case 1:
				return config.showStats();
			case 2:
				return config.showQuest();
			case 3:
				return config.showInventory();
			case 4:
				return config.showEquipment();
			case 5:
				return config.showPrayer();
			case 6:
				return config.showMagic();
			case 7:
				return config.showClanChat();
			case 8:
				return config.showFriends();
			case 9:
				return config.showAccount();
			case 10:
				return config.showLogout();
			case 11:
				return config.showSettings();
			case 12:
				return config.showEmotes();
			case 13:
				return config.showMusic();
			default:
				return true;
		}
	}

	private int getTabKeyCode(int tab)
	{
		for (int varbit : TAB_KEY_VARBITS[tab])
		{
			int keyCode = client.getVarbitValue(varbit);
			if (isSupportedKeyCode(keyCode))
			{
				return keyCode;
			}
		}

		return 0;
	}

	private static boolean isSupportedKeyCode(int keyCode)
	{
		return keyCode >= 1 && keyCode <= 13;
	}

	private Widget getTabWidget(int tab)
	{
		for (int widgetId : TAB_WIDGET_CANDIDATES[tab])
		{
			Widget widget = client.getWidget(widgetId);
			if (isRenderableWidget(widget))
			{
				return widget;
			}
		}

		return null;
	}

	private static boolean isRenderableWidget(Widget widget)
	{
		if (widget == null || widget.isHidden() || widget.isSelfHidden())
		{
			return false;
		}

		Rectangle bounds = widget.getBounds();
		return bounds != null && bounds.width > 0 && bounds.height > 0;
	}

	private String getDisplayLabel(int keyCode)
	{
		if (!isFkeyRemappingEnabled())
		{
			return getStockKeyLabel(keyCode);
		}

		ModifierlessKeybind keybind = getRemappedKeybind(keyCode);
		if (keybind == null || new ModifierlessKeybind(KeyEvent.VK_UNDEFINED, 0).equals(keybind))
		{
			return getStockKeyLabel(keyCode);
		}

		return compactKeyLabel(keybind.toString());
	}

	private boolean isFkeyRemappingEnabled()
	{
		Boolean enabled = configManager.getConfiguration(KEY_REMAPPING_GROUP, FKEY_REMAP_KEY, boolean.class);
		return enabled != null && enabled;
	}

	private ModifierlessKeybind getRemappedKeybind(int keyCode)
	{
		String configKey = getKeyRemappingConfigKey(keyCode);
		if (configKey == null)
		{
			return null;
		}

		ModifierlessKeybind configured = configManager.getConfiguration(
			KEY_REMAPPING_GROUP,
			configKey,
			ModifierlessKeybind.class);

		return configured == null ? getDefaultRemappedKeybind(keyCode) : configured;
	}

	private static String getKeyRemappingConfigKey(int keyCode)
	{
		if (keyCode >= 1 && keyCode <= 12)
		{
			return "f" + keyCode;
		}
		if (keyCode == 13)
		{
			return "esc";
		}

		return null;
	}

	private static ModifierlessKeybind getDefaultRemappedKeybind(int keyCode)
	{
		switch (keyCode)
		{
			case 1:
				return new ModifierlessKeybind(KeyEvent.VK_1, 0);
			case 2:
				return new ModifierlessKeybind(KeyEvent.VK_2, 0);
			case 3:
				return new ModifierlessKeybind(KeyEvent.VK_3, 0);
			case 4:
				return new ModifierlessKeybind(KeyEvent.VK_4, 0);
			case 5:
				return new ModifierlessKeybind(KeyEvent.VK_5, 0);
			case 6:
				return new ModifierlessKeybind(KeyEvent.VK_6, 0);
			case 7:
				return new ModifierlessKeybind(KeyEvent.VK_7, 0);
			case 8:
				return new ModifierlessKeybind(KeyEvent.VK_8, 0);
			case 9:
				return new ModifierlessKeybind(KeyEvent.VK_9, 0);
			case 10:
				return new ModifierlessKeybind(KeyEvent.VK_0, 0);
			case 11:
				return new ModifierlessKeybind(KeyEvent.VK_MINUS, 0);
			case 12:
				return new ModifierlessKeybind(KeyEvent.VK_EQUALS, 0);
			case 13:
				return new ModifierlessKeybind(KeyEvent.VK_ESCAPE, 0);
			default:
				return null;
		}
	}

	private static String getStockKeyLabel(int keyCode)
	{
		if (keyCode >= 1 && keyCode <= 12)
		{
			return "F" + keyCode;
		}
		if (keyCode == 13)
		{
			return "Esc";
		}

		return null;
	}

	private static String compactKeyLabel(String label)
	{
		switch (label)
		{
			case "Escape":
				return "Esc";
			case "Minus":
				return "-";
			case "Equals":
				return "=";
			default:
				return label;
		}
	}

	private float getOverlayAlpha(boolean shouldShow)
	{
		if (shouldShow)
		{
			wasShowing = true;
			fadeStartedAtMillis = 0;
			return 1.0f;
		}

		if (fadeStartedAtMillis == 0)
		{
			fadeStartedAtMillis = System.currentTimeMillis();
			return 1.0f;
		}

		long elapsed = System.currentTimeMillis() - fadeStartedAtMillis;
		if (elapsed >= FADE_DURATION_MILLIS)
		{
			return 0.0f;
		}

		return 1.0f - elapsed / (float) FADE_DURATION_MILLIS;
	}

	private void clearFadeState()
	{
		wasShowing = false;
		fadeStartedAtMillis = 0;
	}

	private int getOpenSidePanelTab()
	{
		int tab = client.getVarcIntValue(VarClientID.TOPLEVEL_PANEL);
		return tab >= 0 && tab < SIDE_PANEL_COUNT ? tab : -1;
	}

	private void renderLabel(Graphics2D graphics, Rectangle bounds, String label, int tab)
	{
		Font originalFont = graphics.getFont();
		Font labelFont = originalFont.deriveFont(config.fontStyle().getAwtStyle(), (float) config.fontSize());
		graphics.setFont(labelFont);

		FontMetrics metrics = graphics.getFontMetrics();
		int textWidth = metrics.stringWidth(label);
		int textHeight = metrics.getAscent();
		int labelWidth = textWidth + LABEL_PADDING_X * 2;
		int labelHeight = textHeight + LABEL_PADDING_Y * 2;
		int x = getLabelX(bounds, labelWidth) + config.xOffset();
		int y = getLabelY(bounds, labelHeight) + config.yOffset();

		if (config.showBackground())
		{
			int radius = config.backgroundRadius() * 2;
			graphics.setColor(config.backgroundColor());
			graphics.fillRoundRect(x, y, labelWidth, labelHeight, radius, radius);
		}

		int textX = x + LABEL_PADDING_X;
		int textY = y + LABEL_PADDING_Y + metrics.getAscent();
		renderTextOutline(graphics, label, textX, textY);
		graphics.setColor(getLabelColor(tab));
		graphics.drawString(label, textX, textY);
		graphics.setFont(originalFont);
	}

	private void renderTextOutline(Graphics2D graphics, String label, int x, int y)
	{
		int thickness = config.textOutlineThickness();
		if (thickness <= 0)
		{
			return;
		}

		graphics.setColor(Color.BLACK);
		for (int xOffset = -thickness; xOffset <= thickness; xOffset++)
		{
			for (int yOffset = -thickness; yOffset <= thickness; yOffset++)
			{
				if (xOffset == 0 && yOffset == 0)
				{
					continue;
				}

				graphics.drawString(label, x + xOffset, y + yOffset);
			}
		}
	}

	private int getLabelX(Rectangle bounds, int labelWidth)
	{
		switch (config.labelPosition())
		{
			case TOP_LEFT:
			case BOTTOM_LEFT:
				return bounds.x;
			case CENTER:
				return bounds.x + (bounds.width - labelWidth) / 2;
			case TOP_RIGHT:
			case BOTTOM_RIGHT:
			default:
				return bounds.x + bounds.width - labelWidth;
		}
	}

	private int getLabelY(Rectangle bounds, int labelHeight)
	{
		switch (config.labelPosition())
		{
			case BOTTOM_LEFT:
			case BOTTOM_RIGHT:
				return bounds.y + bounds.height - labelHeight;
			case CENTER:
				return bounds.y + (bounds.height - labelHeight) / 2;
			case TOP_LEFT:
			case TOP_RIGHT:
			default:
				return bounds.y;
		}
	}

	private Color getLabelColor(int tab)
	{
		if (!config.individualColors())
		{
			return config.labelColor();
		}

		switch (tab)
		{
			case 0:
				return config.combatColor();
			case 1:
				return config.statsColor();
			case 2:
				return config.questColor();
			case 3:
				return config.inventoryColor();
			case 4:
				return config.equipmentColor();
			case 5:
				return config.prayerColor();
			case 6:
				return config.magicColor();
			case 7:
				return config.clanChatColor();
			case 8:
				return config.friendsColor();
			case 9:
				return config.accountColor();
			case 10:
				return config.logoutColor();
			case 11:
				return config.settingsColor();
			case 12:
				return config.emotesColor();
			case 13:
				return config.musicColor();
			default:
				return config.labelColor();
		}
	}

	private static final class Label
	{
		private final String text;
		private final int keyCode;

		private Label(String text, int keyCode)
		{
			this.text = text;
			this.keyCode = keyCode;
		}
	}
}
