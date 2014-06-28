package org.twbbs.pccprogram.scratchpp;

import java.awt.Color;
import java.awt.Font;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import org.twbbs.pccprogram.scratchpp.object.integral.SignedInt;
import org.twbbs.pccprogram.scratchpp.syntax.function.MainFunction;

/**
 * The interpreter.
 * 
 * @author johnchen902
 */
public class Interpreter {

	private JFrame frame;
	private JTextArea textArea;

	public Interpreter() {
		frame = new JFrame("console");
		textArea = new JTextArea(25, 80);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setBackground(Color.BLACK);
		textArea.setForeground(Color.WHITE);
		textArea.setCaretColor(Color.WHITE);
		textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));

		frame.add(new JScrollPane(textArea));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * Interpret the main function.
	 * 
	 * @param main
	 *            the main function
	 */
	public void execute(MainFunction main) {
		Objects.requireNonNull(main);
		if (frame.isVisible()) {
			JOptionPane.showMessageDialog(null, "Already running");
			return;
		}

		textArea.setText("");

		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);

		RuntimeEnvironment environment = new RuntimeEnvironment();

		new SwingWorker<SignedInt, String>() {
			private long start, end;

			@Override
			protected SignedInt doInBackground() {
				start = System.currentTimeMillis();
				Object o;
				try {
					o = main.invoke(environment);
				} finally {
					end = System.currentTimeMillis();
				}

				return (o instanceof SignedInt) ? (SignedInt) o : null;
			}

			protected void done() {
				StringBuilder s = new StringBuilder("Program returned ");
				SignedInt i = null;
				Throwable err = null;
				try {
					i = get();
				} catch (InterruptedException | ExecutionException e) {
					if (e instanceof ExecutionException)
						err = e.getCause();
				}
				if (i != null) {
					s.append(i).append(" (0x")
							.append(Integer.toHexString(i.getValue()))
							.append(')');
				} else {
					s.append("a non-int value");
				}
				s.append("   execution time : ").append((end - start) / 1000.0)
						.append(" s").append(System.lineSeparator());
				if (err != null) {
					s.append(err).append(System.lineSeparator());
				}
				textArea.append(s.toString());
			};
		}.execute();
	}

	/**
	 * The runtime environment.
	 * 
	 * @author johnchen902
	 */
	public class RuntimeEnvironment {
		private RuntimeEnvironment() {
		}

		/**
		 * Writes a string to standard output.
		 *
		 * @param str
		 *            {@code String} to be written
		 */
		public void write(String str) {
			SwingUtilities.invokeLater(() -> textArea.append(str));
		}
	}
}
