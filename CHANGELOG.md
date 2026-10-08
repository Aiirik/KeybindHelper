# Changelog

## v1.0.0 - 08-Oct-2026

Initial release.

### Added

- Show side-panel tab keybind labels over tab icons.
- Read tab keybinds from RuneLite/OSRS client varbits.
- Use RuneLite Key Remapping labels when F-key remapping is enabled.
- Fall back to stock F-key labels when Key Remapping is disabled or unset.
- Support fixed classic, resizable classic, and resizable modern layouts.
- Add configurable label font size, font style, position, offsets, and background corner radius.
- Add global label and background color settings.
- Add per-tab visibility toggles.
- Add optional per-tab label colors.
- Add optional combat-only display mode.
- Add configurable combat hide delay and optional smooth fade-out after the delay.
- Add option to hide the currently open side-panel tab label.
- Add configurable text outline thickness.

### Technical

- Uses RuneLite overlay rendering only.
- Avoids input injection, menu entry modification, external processes, reflection, HTTP requests, and file IO.
- Uses event-driven combat state tracking for combat-only mode.
