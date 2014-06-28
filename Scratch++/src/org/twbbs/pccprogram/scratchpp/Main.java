package org.twbbs.pccprogram.scratchpp;

import java.awt.BorderLayout;
import java.util.function.Supplier;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import org.twbbs.pccprogram.scratchpp.syntax.function.MainFunction;

/**
 * <p>
 * The snow glows white on the mountain tonight<br>
 * Not a footprint to be seen<br>
 * A kingdom of isolation<br>
 * And it looks like I'm the queen<br>
 * The wind is howling like the swirling storm inside<br>
 * Couldn't keep it in, heavens know I tried<br>
 * 
 * <p>
 * Don't let them in<br>
 * Don't let them see<br>
 * Be the good girl you always have to be<br>
 * Conceal, don't feel<br>
 * Don't let them know<br>
 * Well, now they know<br>
 * 
 * <p>
 * Let it go!<br>
 * Let it go!<br>
 * Can't hold it back anymore<br>
 * Let it go!<br>
 * Let it go!<br>
 * Turn away and slam the door<br>
 * 
 * <p>
 * I don't care<br>
 * What they're going to say<br>
 * Let the storm rage on<br>
 * The cold never bothered me anyway<br>
 * 
 * @author johnchen902
 */
public class Main {

	/**
	 * <p>
	 * It's funny how some distance<br>
	 * Makes everything seems small<br>
	 * And the fears that once controlled me<br>
	 * Can't get to at all<br>
	 * It's time to see what I can do<br>
	 * To paste the limits and break through<br>
	 * No rights, no wrongs, no rules for me<br>
	 * I'm free<br>
	 * 
	 * <p>
	 * Let it go!<br>
	 * Let it go!<br>
	 * I'm one with the wind and sky<br>
	 * Let it go!<br>
	 * Let it go!<br>
	 * You'll never see me cry<br>
	 * Here I stand and here I'll stay<br>
	 * Let the storm rage on<br>
	 * 
	 * <p>
	 * My power flurries through the air into the ground<br>
	 * My soul is spiraling in frozen fractals all around<br>
	 * And one thought crystallizes like and icy blast<br>
	 * I'm never going back. The past is in the past<br>
	 * 
	 * <p>
	 * Let it go!<br>
	 * Let it go!<br>
	 * I'll rise like a break of dawn<br>
	 * Let it go!<br>
	 * Let it go!<br>
	 * That perfect girl is gone<br>
	 * 
	 * @param args
	 *            <p>
	 *            Here I stand in the light of day<br>
	 *            Let the storm rage on<br>
	 *            The cold never bothered me anyway<br>
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame f = new JFrame("Scratch++");

			DragLayerPanel dlp = new DragLayerPanel();
			dlp.setLayout(new BorderLayout());

			JTabbedPane tabs = new JTabbedPane();
			{

				NewSymbolPanel nsp = NewSymbolPanel
						.createDefaultNewCodePanel(dlp);
				tabs.addTab("1", nsp);

				NewLiteralPanel nlp = new NewLiteralPanel(dlp);
				tabs.addTab("2", nlp);
			}
			dlp.add(tabs, BorderLayout.WEST);

			SymbolPanel sp = new SymbolPanel(dlp);
			dlp.add(new JScrollPane(sp), BorderLayout.CENTER);

			f.add(dlp, BorderLayout.CENTER);
			f.add(createToolBar(sp::getMainFunction), BorderLayout.SOUTH);

			f.setSize(800, 600);

			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setLocationByPlatform(true);

			f.setVisible(true);
		});
	}

	private static JComponent createToolBar(Supplier<MainFunction> sup) {
		JToolBar bar = new JToolBar();
		Interpreter interpreter = new Interpreter();
		JButton button = new JButton("Run");
		button.addActionListener(e -> {
			MainFunction main = sup.get();
			if (main == null) {
				JOptionPane.showMessageDialog(button, "No main function");
			} else {
				interpreter.execute(main);
			}
		});
		bar.add(button);
		return bar;
	}
}
