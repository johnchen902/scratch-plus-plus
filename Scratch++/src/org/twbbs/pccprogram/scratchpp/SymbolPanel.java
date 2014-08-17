package org.twbbs.pccprogram.scratchpp;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Predicate;

import javax.swing.JPanel;

import org.twbbs.pccprogram.scratchpp.syntax.Symbol;
import org.twbbs.pccprogram.scratchpp.syntax.function.MainFunction;

/**
 * The panel for operating symbols
 * 
 * @author johnchen902
 */
@SuppressWarnings("serial")
public class SymbolPanel extends JPanel implements DragDropTarget {

	private DragLayerPanel dlp;
	private List<Symbol> symbols;

	public SymbolPanel(DragLayerPanel dlp) {
		this.dlp = dlp;
		symbols = new ArrayList<>();
		symbols.add(new MainFunction(10, 10));
		addMouseListener(new MyMouseAdapter());

		dlp.addTarget(this);
	}

	private Symbol findTarget(Predicate<Symbol> predicate) {
		for (Symbol s : symbols) {
			Symbol t = findTarget(s, predicate);
			if (t != null)
				return t;
		}
		return null;
	}

	private Symbol findTarget(Symbol s, Predicate<Symbol> predicate) {
		for (Symbol c : s.getInners()) {
			Symbol t = findTarget(c, predicate);
			if (t != null)
				return t;
		}
		return predicate.test(s) ? s : null;
	}

	@Override
	public Dimension getPreferredSize() {
		Dimension d = super.getPreferredSize();
		d.width = symbols.stream().mapToInt(x -> x.getX() + x.getWidth()).max()
				.orElse(0) + 10;
		d.height = symbols.stream().mapToInt(x -> x.getY() + x.getHeight())
				.max().orElse(0) + 10;
		return d;
	}

	private class MyMouseAdapter extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			Symbol dragged = findTarget(x -> x.getShape()
					.contains(e.getPoint()));
			if (dragged != null) {
				if (dragged.getParent() != null)
					dragged.getParent().remove(dragged);
				else
					symbols.remove(dragged);
				dlp.startDrag(SymbolPanel.this, dragged, e.getPoint());
				revalidate();
				repaint();
			}
		}
	}

	@Override
	public boolean canAccept(Symbol symbol, Point p) {
		if (symbol instanceof MainFunction
				&& symbols.stream().anyMatch(x -> x instanceof MainFunction))
			return false;
		if (findTarget(x -> x.isDroppable(symbol, p)) != null)
			return true;
		if (symbol.getX() < 0)
			return false;
		if (symbol.getY() < 0)
			return false;
		return true;
	}

	@Override
	public void drop(Symbol symbol, Point p) {
		Symbol s = findTarget(x -> x.isDroppable(symbol, p));
		if (s == null) {
			symbols.add(0, symbol);
		} else {
			s.add(symbol, p);
		}

		revalidate();
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g0) {
		super.paintComponent(g0);
		Graphics2D g = (Graphics2D) g0;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		for (ListIterator<Symbol> iterator = symbols.listIterator(symbols
				.size()); iterator.hasPrevious();)
			iterator.previous().paint(g);
	}

	public MainFunction getMainFunction() {
		return (MainFunction) symbols.stream()
				.filter(x -> x instanceof MainFunction).findAny().orElse(null);
	}
}
