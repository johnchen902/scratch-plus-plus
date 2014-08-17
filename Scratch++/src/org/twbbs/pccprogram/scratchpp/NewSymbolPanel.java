package org.twbbs.pccprogram.scratchpp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.swing.JPanel;

import org.twbbs.pccprogram.scratchpp.syntax.Symbol;
import org.twbbs.pccprogram.scratchpp.syntax.expression.AddExpression;
import org.twbbs.pccprogram.scratchpp.syntax.expression.CastExpression;
import org.twbbs.pccprogram.scratchpp.syntax.expression.DivideExpression;
import org.twbbs.pccprogram.scratchpp.syntax.expression.MinusExpression;
import org.twbbs.pccprogram.scratchpp.syntax.expression.ModuleExpression;
import org.twbbs.pccprogram.scratchpp.syntax.expression.MultiplyExpression;
import org.twbbs.pccprogram.scratchpp.syntax.function.MainFunction;
import org.twbbs.pccprogram.scratchpp.syntax.statement.MagicPrint;

/**
 * The panel for creating new symbols.
 * 
 * @author johnchen902
 */
@SuppressWarnings("serial")
public class NewSymbolPanel extends JPanel implements DragDropTarget {

	private List<Symbol> symbols;

	public NewSymbolPanel(DragLayerPanel dlp) {
		symbols = new ArrayList<>();

		setBackground(Color.WHITE);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Optional<Symbol> opt = symbols.stream()
						.filter(x -> x.getShape().contains(e.getPoint()))
						.findAny();
				if (opt.isPresent()) {
					dlp.startDrag(NewSymbolPanel.this, opt.get().clone(),
							e.getPoint());
				}
			}
		});

		dlp.addTarget(this);
	}

	public void addSymbol(Symbol s) {
		symbols.add(Objects.requireNonNull(s));
		updateSymbolLocation();
	}

	public void addSymbol(Symbol s, int index) {
		symbols.add(index, Objects.requireNonNull(s));
		updateSymbolLocation();
	}

	public void removeSymbol(Symbol s) {
		if (!symbols.remove(Objects.requireNonNull(s)))
			throw new IllegalArgumentException("no such symbol");
		updateSymbolLocation();
	}

	public void removeSymbol(int index) {
		symbols.remove(index);
		updateSymbolLocation();
	}

	public int getSymbolCount() {
		return symbols.size();
	}

	private void updateSymbolLocation() {
		int yy = 10;
		for (Symbol s : symbols) {
			s.setX(10);
			s.setY(yy);
			yy += s.getHeight() + 8;
		}
		revalidate();
		repaint();
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

	@Override
	public boolean canAccept(Symbol symbol, Point p) {
		return true;
	}

	@Override
	public void drop(Symbol symbol, Point p) {
		// kill the symbol, mua he he he
	}

	@Override
	protected void paintComponent(Graphics g0) {
		super.paintComponent(g0);
		Graphics2D g = (Graphics2D) g0;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		symbols.forEach(x -> x.paint(g));
	}

	/**
	 * Create a {@code NewCodePanel} with default symbols.
	 */
	public static NewSymbolPanel createDefaultNewCodePanel(DragLayerPanel dlp) {
		NewSymbolPanel ncp = new NewSymbolPanel(dlp);
		ncp.addSymbol(new MainFunction(10, 10));
		ncp.addSymbol(new MagicPrint(10, 10));
		ncp.addSymbol(new CastExpression(10, 10));
		ncp.addSymbol(new AddExpression(10, 10));
		ncp.addSymbol(new MinusExpression(10, 10));
		ncp.addSymbol(new MultiplyExpression(10, 10));
		ncp.addSymbol(new DivideExpression(10, 10));
		ncp.addSymbol(new ModuleExpression(10, 10));
		return ncp;
	}
}
