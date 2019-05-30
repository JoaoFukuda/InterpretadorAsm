// Contém a janela principal do programa
package src.grafico;

import src.cpn.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class MainFrame
{
	private JFrame f;

	public MainFrame()
	{
		f = new JFrame("Interpretador Assembly");
		f.setSize(250, 250);

		f.setVisible(true);
	}
}
