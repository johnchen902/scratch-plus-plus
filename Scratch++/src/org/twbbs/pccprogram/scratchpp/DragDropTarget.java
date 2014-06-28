package org.twbbs.pccprogram.scratchpp;

import java.awt.Point;

import org.twbbs.pccprogram.scratchpp.syntax.Symbol;

/**
 * Component that can drag and drop symbols.
 * 
 * @author johnchen902
 * @see DragLayerPanel
 * @see <a href="https://en.wikipedia.org/wiki/DDT"> <strike>
 *      dichlorodiphenyltrichloroethane </strike> </a>
 */
public interface DragDropTarget {
	/**
	 * Check whether a symbol can be dropped.
	 * 
	 * @param symbol
	 *            the symbol
	 * @return <code>true</code> if can be dropped; <code>false</code> otherwise
	 */
	public boolean canAccept(Symbol symbol);

	/**
	 * Drop the symbol.
	 * 
	 * @param symbol
	 *            the symbol to be dropped
	 * @param p
	 *            the location of the mouse
	 */
	public void drop(Symbol symbol, Point p);
}
