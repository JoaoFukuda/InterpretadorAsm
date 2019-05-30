// Contém a janela principal do programa
package src.grafico;

import src.cpn.*;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class MainFrame extends JFrame implements ActionListener {
	JMenuBar mb;
	JMenu file;
	JMenuItem open;
	JTextArea ta;

	private void Init() {
		open = new JMenuItem("Abrir arquivo");
		open.addActionListener(this);

		file = new JMenu("Arquivo");
		file.add(open);

		mb = new JMenuBar();
		mb.setBounds(0,0,800,20);
		mb.add(file);

		ta = new JTextArea(800,800);
		ta.setBounds(0,20,800,800);

		add(mb);
		add(ta);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == open) {
			JFileChooser fc = new JFileChooser();
			int i = fc.showOpenDialog(this);

			if(i == JFileChooser.APPROVE_OPTION) {
				File f = fc.getSelectedFile();
				String filepath = f.getPath();
				try {
					BufferedReader br = new BufferedReader(new FileReader(filepath));
					String s1 = "",s2 = "";
					while((s1 = br.readLine()) != null) {
						s2 += s1 + "\n";
					}
					ta.setText(s2);
					br.close();
				}catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	public MainFrame()
	{
		Init();

		setTitle("Interpretador Assembly");
        setSize(500,500);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

		//TODO: Código
	}
}
