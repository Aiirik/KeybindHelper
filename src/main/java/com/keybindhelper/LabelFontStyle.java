package com.keybindhelper;

import java.awt.Font;

public enum LabelFontStyle
{
	PLAIN("Plain", Font.PLAIN),
	BOLD("Bold", Font.BOLD),
	ITALIC("Italic", Font.ITALIC),
	BOLD_ITALIC("Bold italic", Font.BOLD | Font.ITALIC);

	private final String name;
	private final int awtStyle;

	LabelFontStyle(String name, int awtStyle)
	{
		this.name = name;
		this.awtStyle = awtStyle;
	}

	int getAwtStyle()
	{
		return awtStyle;
	}

	@Override
	public String toString()
	{
		return name;
	}
}
