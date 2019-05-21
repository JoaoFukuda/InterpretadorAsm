package src.grafico;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class MainFrame
{
	private JFrame f;
	private JTextArea textArea;
	private JButton button;

	public MainFrame()
	{
		f = new JFrame("Janela-teste");
		f.setSize(250, 250);
		f.setLocation(300,200);

		textArea = new JTextArea(10, 40);
		f.getContentPane().add(BorderLayout.CENTER, textArea);
		
		button = new JButton("Hit me senpai");
		f.getContentPane().add(BorderLayout.SOUTH, button);

		button.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				textArea.append("Nyan!\n");

			}
		});

		f.setVisible(true);
	}
}
