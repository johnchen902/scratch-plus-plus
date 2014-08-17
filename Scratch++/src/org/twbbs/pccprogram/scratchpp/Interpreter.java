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

import org.twbbs.pccprogram.scratchpp.object.primitive.SignedInt;
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
				SignedInt i = null;
				Throwable err = null;
				try {
					i = get();
				} catch (InterruptedException e) {
					System.err.println(e);
				} catch (ExecutionException e) {
					if (e instanceof ExecutionException)
						err = e.getCause();
					else
						err = e;
				}
				String s;
				if (err != null) {
					if (err instanceof CompileException)
						s = "Compile error: " + err.getMessage();
					else
						s = "Runtime error: " + err;
				} else if (i == null) {
					s = "Program returned a non-int value";
				} else {
					s = "Program returned " + i + " (0x"
							+ Integer.toHexString(i.getValue()) + ")";
				}
				s += System.lineSeparator() + "Execution time : "
						+ (end - start) / 1000.0 + " s"
						+ System.lineSeparator();
				textArea.append(s);
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
