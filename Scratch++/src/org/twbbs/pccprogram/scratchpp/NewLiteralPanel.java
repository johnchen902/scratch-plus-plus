package org.twbbs.pccprogram.scratchpp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.twbbs.pccprogram.scratchpp.syntax.PrimitiveType;
import org.twbbs.pccprogram.scratchpp.syntax.Symbol;
import org.twbbs.pccprogram.scratchpp.syntax.literal.FloatingLiteral;
import org.twbbs.pccprogram.scratchpp.syntax.literal.IntegerLiteral;

/**
 * The panel for creating new symbols where the set of the symbols is not
 * predefined.
 * 
 * @author johnchen902
 */
@SuppressWarnings("serial")
public class NewLiteralPanel extends JPanel {
	private NewSymbolPanel nsp;

	public NewLiteralPanel(DragLayerPanel dlp) {
		super(new BorderLayout());
		JPanel pnUp = new JPanel(new GridLayout(0, 1));

		JButton btInteger = new JButton("Integer Literal");
		btInteger.addActionListener(new MyActionListener(IntegerLiteral::new));
		pnUp.add(btInteger);

		JButton btFloat = new JButton("Floating Literal");
		btFloat.addActionListener(new MyActionListener(FloatingLiteral::new));
		pnUp.add(btFloat);

		JButton btType = new JButton("Primitive Type");
		btType.addActionListener(new MyActionListener(PrimitiveType::new));
		pnUp.add(btType);

		JButton btClear = new JButton("Clear");
		btClear.addActionListener(x -> {
			while (nsp.getSymbolCount() > 0) {
				nsp.removeSymbol(nsp.getSymbolCount() - 1);
			}
		});
		pnUp.add(btClear);

		add(pnUp, BorderLayout.NORTH);

		nsp = new NewSymbolPanel(dlp);
		add(new JScrollPane(nsp));
	}

	@Override
	public Dimension getPreferredSize() {
		Dimension d = super.getPreferredSize();
		d.width = 150;
		return d;
	}

	private class MyActionListener implements ActionListener {
		private final Func func;

		private MyActionListener(Func func) {
			this.func = Objects.requireNonNull(func);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String str = JOptionPane.showInputDialog(NewLiteralPanel.this,
					"Input?");
			if (str != null) {
				Symbol symbol;
				try {
					symbol = func.create(0, 0, str);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(NewLiteralPanel.this,
							e1.toString());
					return;
				}
				nsp.addSymbol(symbol, 0);
			}
		}
	}

	private static interface Func {
		public Symbol create(int x, int y, String value);
	}
}
