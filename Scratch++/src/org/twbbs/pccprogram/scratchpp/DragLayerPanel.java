package org.twbbs.pccprogram.scratchpp;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.twbbs.pccprogram.scratchpp.syntax.Symbol;

/**
 * A panel that handles dragging of symbols.
 * 
 * @author johnchen902
 */
@SuppressWarnings("serial")
public class DragLayerPanel extends JPanel {

	private List<DragDropTarget> list;
	private Symbol dragged;

	private Point origin;
	private Point current;
	private Point offset;

	public DragLayerPanel() {
		list = new ArrayList<>();
	}

	/**
	 * Add a target.
	 * 
	 * @param comp
	 */
	public <T extends Component & DragDropTarget> void addTarget(T comp) {
		MyMouseAdapter mma = new MyMouseAdapter();
		comp.addMouseListener(mma);
		comp.addMouseMotionListener(mma);
		list.add(comp);
	}

	/**
	 * Initialize a drag.
	 * 
	 * @param from
	 *            which component
	 * @param dragged
	 *            which symbol
	 * @param origin
	 *            where were the mouse
	 */
	public <T extends Component & DragDropTarget> void startDrag(T from,
			Symbol dragged, Point origin) {
		this.dragged = Objects.requireNonNull(dragged);
		this.origin = SwingUtilities.convertPoint(from, origin, this);
		this.current = new Point(this.origin);
		this.offset = new Point(dragged.getX() - origin.x, dragged.getY()
				- origin.y);
		dragged.setX(this.origin.x + this.offset.x);
		dragged.setY(this.origin.y + this.offset.y);
		repaint();
	}

	private class MyMouseAdapter extends MouseAdapter {

		@Override
		public void mouseDragged(MouseEvent e) {
			if (dragged != null) {
				current = SwingUtilities.convertPoint(e.getComponent(),
						e.getPoint(), DragLayerPanel.this);
				dragged.setX(current.x + offset.x);
				dragged.setY(current.y + offset.y);
				repaint();
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (dragged != null) {
				Optional<DragDropTarget> target = list
						.stream()
						.filter(ddt -> {
							Component comp = (Component) ddt;
							Point p = SwingUtilities.convertPoint(
									DragLayerPanel.this, current, comp);
							if (!new Rectangle(comp.getSize()).contains(p))
								return false;
							dragged.setX(p.x + offset.x);
							dragged.setY(p.y + offset.y);
							return ddt.canAccept(dragged);
						}).findFirst();
				if (target.isPresent()) {
					DragDropTarget ddt = target.get();
					Component comp = (Component) ddt;
					Point p = SwingUtilities.convertPoint(DragLayerPanel.this,
							current, comp);
					dragged.setX(p.x + offset.x);
					dragged.setY(p.y + offset.y);
					ddt.drop(dragged, p);
				} else {
					Component comp = e.getComponent();
					DragDropTarget ddt = (DragDropTarget) comp;
					Point p = SwingUtilities.convertPoint(DragLayerPanel.this,
							origin, comp);
					dragged.setX(p.x + offset.x);
					dragged.setY(p.y + offset.y);
					ddt.drop(dragged, p);
				}
				dragged = null;
				origin = offset = null;
				repaint();
			}
		}
	}

	@Override
	public void paint(Graphics g0) {
		super.paint(g0);
		Graphics2D g = (Graphics2D) g0;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		if (dragged != null) {
			Shape shadow = dragged.getAllShape();
			shadow = AffineTransform.getTranslateInstance(10, 10)
					.createTransformedShape(shadow);
			g.setColor(new Color(0x7f000000, true));
			g.fill(shadow);

			dragged.paint(g);
		}
	}
}
