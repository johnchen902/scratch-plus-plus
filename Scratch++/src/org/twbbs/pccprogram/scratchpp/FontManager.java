package org.twbbs.pccprogram.scratchpp;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

/**
 * A manager of font.
 * 
 * @author johnchen902
 */
public class FontManager {

	private static final Font FONT = new Font("consolas", Font.BOLD, 14);

	/**
	 * Get the width of a specified text
	 * 
	 * @param text
	 *            the specified text
	 * @return the width
	 */
	public static int getStringWidth(String text) {
		FontRenderContext frc = new FontRenderContext(new AffineTransform(),
				true, false);
		return (int) getFont().getStringBounds(text, frc).getWidth();
	}

	/**
	 * Get the height of a specified text
	 * 
	 * @param text
	 *            the specified text
	 * @return the height
	 */
	public static int getStringHeight(String text) {
		FontRenderContext frc = new FontRenderContext(new AffineTransform(),
				true, false);
		return (int) getFont().getStringBounds(text, frc).getHeight();
	}

	/**
	 * Get the font
	 * 
	 * @return the font
	 */
	public static Font getFont() {
		return FONT;
	}
}
