package com.keybindhelper;

import java.awt.Color;
import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import net.runelite.client.config.Range;

@ConfigGroup("keybind-helper")
public interface KeybindHelperConfig extends Config
{
	@ConfigSection(
		name = "Appearance",
		description = "Controls how keybind labels are drawn.",
		position = 0
	)
	String appearanceSection = "appearance";

	@ConfigSection(
		name = "Shown tabs",
		description = "Controls which side-panel tabs show keybind labels.",
		position = 1
	)
	String shownTabsSection = "shownTabs";

	@ConfigItem(
		position = 0,
		keyName = "onlyShowInCombat",
		name = "Only show in combat",
		description = "Only shows keybind labels while you are in combat.",
		section = appearanceSection
	)
	default boolean onlyShowInCombat()
	{
		return false;
	}

	@Range(
		min = 0,
		max = 10
	)
	@ConfigItem(
		position = 1,
		keyName = "combatHideDelay",
		name = "Combat hide delay",
		description = "Ticks labels remain visible after combat ends when combat-only mode is enabled.",
		section = appearanceSection
	)
	default int combatHideDelay()
	{
		return 8;
	}

	@ConfigItem(
		position = 2,
		keyName = "fadeOutAfterCombat",
		name = "Fade out after combat",
		description = "Smoothly fades labels after the combat hide delay instead of hiding them abruptly.",
		section = appearanceSection
	)
	default boolean fadeOutAfterCombat()
	{
		return false;
	}

	@ConfigItem(
		position = 3,
		keyName = "hideOpenTabLabel",
		name = "Hide open tab label",
		description = "Hides the label on the currently selected side-panel tab.",
		section = appearanceSection
	)
	default boolean hideOpenTabLabel()
	{
		return false;
	}

	@ConfigItem(
		position = 4,
		keyName = "showBackground",
		name = "Show background",
		description = "Draws a small background behind each keybind label.",
		section = appearanceSection
	)
	default boolean showBackground()
	{
		return true;
	}

	@Range(
		min = 8,
		max = 20
	)
	@ConfigItem(
		position = 5,
		keyName = "fontSize",
		name = "Font size",
		description = "Size of the keybind label text.",
		section = appearanceSection
	)
	default int fontSize()
	{
		return 15;
	}

	@ConfigItem(
		position = 6,
		keyName = "fontStyle",
		name = "Font style",
		description = "Style of the keybind label text.",
		section = appearanceSection
	)
	default LabelFontStyle fontStyle()
	{
		return LabelFontStyle.BOLD;
	}

	@Range(
		min = 0,
		max = 4
	)
	@ConfigItem(
		position = 7,
		keyName = "textOutlineThickness",
		name = "Text outline thickness",
		description = "Thickness of the black outline around label text. Use 0 to disable.",
		section = appearanceSection
	)
	default int textOutlineThickness()
	{
		return 1;
	}

	@Range(
		min = 0,
		max = 16
	)
	@ConfigItem(
		position = 8,
		keyName = "backgroundRadius",
		name = "Background radius",
		description = "Corner radius of the label background. Use 0 for square corners.",
		section = appearanceSection
	)
	default int backgroundRadius()
	{
		return 4;
	}

	@ConfigItem(
		position = 9,
		keyName = "labelPosition",
		name = "Label position",
		description = "Where to place each keybind label inside the menu icon.",
		section = appearanceSection
	)
	default LabelPosition labelPosition()
	{
		return LabelPosition.TOP_LEFT;
	}

	@Range(
		min = -20,
		max = 20
	)
	@ConfigItem(
		position = 10,
		keyName = "xOffset",
		name = "X offset",
		description = "Moves keybind labels left or right.",
		section = appearanceSection
	)
	default int xOffset()
	{
		return 0;
	}

	@Range(
		min = -20,
		max = 20
	)
	@ConfigItem(
		position = 11,
		keyName = "yOffset",
		name = "Y offset",
		description = "Moves keybind labels up or down.",
		section = appearanceSection
	)
	default int yOffset()
	{
		return 0;
	}

	@Alpha
	@ConfigItem(
		position = 12,
		keyName = "labelColor",
		name = "Label color",
		description = "Color used for all labels unless individual colors are enabled.",
		section = appearanceSection
	)
	default Color labelColor()
	{
		return Color.WHITE;
	}

	@Alpha
	@ConfigItem(
		position = 13,
		keyName = "backgroundColor",
		name = "Background color",
		description = "Color of the label background.",
		section = appearanceSection
	)
	default Color backgroundColor()
	{
		return new Color(0, 0, 0, 200);
	}

	@ConfigItem(
		position = 99,
		keyName = "individualColors",
		name = "Individual colors",
		description = "Uses the per-tab label colors below instead of the global label color.",
		section = shownTabsSection
	)
	default boolean individualColors()
	{
		return false;
	}

	@ConfigItem(position = 100, keyName = "showCombat", name = "Combat", description = "Shows the combat tab keybind label.", section = shownTabsSection)
	default boolean showCombat() { return true; }

	@Alpha
	@ConfigItem(position = 101, keyName = "combatColor", name = "Combat color", description = "Color of the combat tab label.", section = shownTabsSection)
	default Color combatColor() { return Color.WHITE; }

	@ConfigItem(position = 102, keyName = "showStats", name = "Stats", description = "Shows the stats tab keybind label.", section = shownTabsSection)
	default boolean showStats() { return true; }

	@Alpha
	@ConfigItem(position = 103, keyName = "statsColor", name = "Stats color", description = "Color of the stats tab label.", section = shownTabsSection)
	default Color statsColor() { return Color.WHITE; }

	@ConfigItem(position = 104, keyName = "showQuest", name = "Quest", description = "Shows the quest tab keybind label.", section = shownTabsSection)
	default boolean showQuest() { return true; }

	@Alpha
	@ConfigItem(position = 105, keyName = "questColor", name = "Quest color", description = "Color of the quest tab label.", section = shownTabsSection)
	default Color questColor() { return Color.WHITE; }

	@ConfigItem(position = 106, keyName = "showInventory", name = "Inventory", description = "Shows the inventory tab keybind label.", section = shownTabsSection)
	default boolean showInventory() { return true; }

	@Alpha
	@ConfigItem(position = 107, keyName = "inventoryColor", name = "Inventory color", description = "Color of the inventory tab label.", section = shownTabsSection)
	default Color inventoryColor() { return Color.WHITE; }

	@ConfigItem(position = 108, keyName = "showEquipment", name = "Equipment", description = "Shows the equipment tab keybind label.", section = shownTabsSection)
	default boolean showEquipment() { return true; }

	@Alpha
	@ConfigItem(position = 109, keyName = "equipmentColor", name = "Equipment color", description = "Color of the equipment tab label.", section = shownTabsSection)
	default Color equipmentColor() { return Color.WHITE; }

	@ConfigItem(position = 110, keyName = "showPrayer", name = "Prayer", description = "Shows the prayer tab keybind label.", section = shownTabsSection)
	default boolean showPrayer() { return true; }

	@Alpha
	@ConfigItem(position = 111, keyName = "prayerColor", name = "Prayer color", description = "Color of the prayer tab label.", section = shownTabsSection)
	default Color prayerColor() { return Color.WHITE; }

	@ConfigItem(position = 112, keyName = "showMagic", name = "Magic", description = "Shows the magic tab keybind label.", section = shownTabsSection)
	default boolean showMagic() { return true; }

	@Alpha
	@ConfigItem(position = 113, keyName = "magicColor", name = "Magic color", description = "Color of the magic tab label.", section = shownTabsSection)
	default Color magicColor() { return Color.WHITE; }

	@ConfigItem(position = 114, keyName = "showClanChat", name = "Clan chat", description = "Shows the clan chat tab keybind label.", section = shownTabsSection)
	default boolean showClanChat() { return true; }

	@Alpha
	@ConfigItem(position = 115, keyName = "clanChatColor", name = "Clan chat color", description = "Color of the clan chat tab label.", section = shownTabsSection)
	default Color clanChatColor() { return Color.WHITE; }

	@ConfigItem(position = 116, keyName = "showFriends", name = "Friends", description = "Shows the friends tab keybind label.", section = shownTabsSection)
	default boolean showFriends() { return true; }

	@Alpha
	@ConfigItem(position = 117, keyName = "friendsColor", name = "Friends color", description = "Color of the friends tab label.", section = shownTabsSection)
	default Color friendsColor() { return Color.WHITE; }

	@ConfigItem(position = 118, keyName = "showAccount", name = "Account", description = "Shows the account tab keybind label.", section = shownTabsSection)
	default boolean showAccount() { return true; }

	@Alpha
	@ConfigItem(position = 119, keyName = "accountColor", name = "Account color", description = "Color of the account tab label.", section = shownTabsSection)
	default Color accountColor() { return Color.WHITE; }

	@ConfigItem(position = 120, keyName = "showLogout", name = "Logout", description = "Shows the logout tab keybind label.", section = shownTabsSection)
	default boolean showLogout() { return true; }

	@Alpha
	@ConfigItem(position = 121, keyName = "logoutColor", name = "Logout color", description = "Color of the logout tab label.", section = shownTabsSection)
	default Color logoutColor() { return Color.WHITE; }

	@ConfigItem(position = 122, keyName = "showSettings", name = "Settings", description = "Shows the settings tab keybind label.", section = shownTabsSection)
	default boolean showSettings() { return true; }

	@Alpha
	@ConfigItem(position = 123, keyName = "settingsColor", name = "Settings color", description = "Color of the settings tab label.", section = shownTabsSection)
	default Color settingsColor() { return Color.WHITE; }

	@ConfigItem(position = 124, keyName = "showEmotes", name = "Emotes", description = "Shows the emotes tab keybind label.", section = shownTabsSection)
	default boolean showEmotes() { return true; }

	@Alpha
	@ConfigItem(position = 125, keyName = "emotesColor", name = "Emotes color", description = "Color of the emotes tab label.", section = shownTabsSection)
	default Color emotesColor() { return Color.WHITE; }

	@ConfigItem(position = 126, keyName = "showMusic", name = "Music", description = "Shows the music tab keybind label.", section = shownTabsSection)
	default boolean showMusic() { return true; }

	@Alpha
	@ConfigItem(position = 127, keyName = "musicColor", name = "Music color", description = "Color of the music tab label.", section = shownTabsSection)
	default Color musicColor() { return Color.WHITE; }
}
