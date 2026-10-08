# Keybind Helper Overlay

Keybind Helper Overlay shows your side-panel tab keybinds directly over the RuneLite menu icons.

This is useful when learning or changing PvP/PK keybinds, switching between stock RuneLite keybinds and RuneLite's Key Remapping plugin, or running a layout where the tab hotkeys are easy to forget.

## Features

- Shows keybind labels over side-panel tab icons.
- Reads RuneLite/OSRS tab keybinds from the client.
- Uses RuneLite Key Remapping labels when F-key remapping is enabled.
- Falls back to the stock F-key labels when Key Remapping is disabled or unset.
- Supports fixed classic, resizable classic, and resizable modern layouts.
- Optional `Only show in combat` mode.
- Configurable combat hide delay and optional smooth fade-out after the delay.
- Optional hiding of the currently open tab's label.
- Configurable label position, offsets, font size, font style, text outline thickness, background color, and background corner radius.
- Optional per-tab visibility toggles.
- Optional per-tab label colors.

## Configuration

Open RuneLite's plugin settings and search for `Keybind Helper Overlay`.

Available settings:

- `Only show in combat`: hides labels outside combat.
- `Combat hide delay`: controls how many ticks labels remain fully visible after combat.
- `Fade out after combat`: smoothly fades labels after the combat hide delay.
- `Hide open tab label`: hides the label on the currently selected side-panel tab.
- `Show background`: draws a background behind each label.
- `Font size`: controls the label text size.
- `Font style`: controls plain, bold, italic, or bold italic text.
- `Text outline thickness`: controls the black outline around label text.
- `Background radius`: controls square-to-rounded label background corners.
- `Label position`: controls where the label sits inside the tab icon.
- `X offset` and `Y offset`: fine-tune label placement.
- `Label color`: sets the global label color.
- `Background color`: sets the label background color.
- `Individual colors`: enables per-tab label colors.
- `Shown tabs`: toggles each tab label on or off.

## Notes

Keybind Helper Overlay is a passive overlay. It does not inject input, change menu entries, or send actions to the game.

Combat-only mode uses lightweight interaction and hitsplat events to decide when to show labels.

## Other Plugins

Check out my other RuneLite plugins:

- [Area Loot](https://github.com/Aiirik/AreaLoot) - Shows nearby ground loot in a panel and highlights selected item locations.
- [Chat Highlight Player](https://github.com/Aiirik/chathighlightplayer) - Highlights players by clicking their names in chat.
- [Player Examine](https://github.com/Aiirik/PlayerExamine) - Shows visible equipment and combat info for examined players.
- [Private Message Fade](https://github.com/Aiirik/PrivateMessageFade) - Hides split private chat after a configurable idle delay.
- [Staff Rune Overlay](https://github.com/Aiirik/StaffRuneOverlay) - Shows rune overlays for elemental and combination staves.
- [World Title](https://github.com/Aiirik/WorldTitle) - Adds the current world to the RuneLite window title.

## Change log

Click to view the [CHANGELOG](CHANGELOG.md).
