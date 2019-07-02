// Contém a janela principal do programa
package src.grafico;

import src.CPU;
import src.auxi.Compilator;

import java.util.Scanner;

import java.awt.event.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

import javax.swing.*;

public class MainFrame extends JFrame implements ActionListener
{

	private int window_width = 500, window_height = 500,
    first_width = window_width/2;

    CPU cpu;

    String currLine, currTime;
	JMenuBar menuBar;
	JMenu file, run;
	JMenuItem open, compiler;
	JMenuItem slow, step, undo, restart;
	JTextArea codeText;
	JTextField flags = new JTextField("000");
	JLabel flagLabel = new JLabel("flags");
	JTextField[] registers = new JTextField[10];
	JLabel[] registerLabels = {
		new JLabel("ax"),
		new JLabel("bx"),
		new JLabel("cx"),
		new JLabel("dx"),
		new JLabel("pc"),
		new JLabel("ir"),
		new JLabel("mar"),
		new JLabel("mbr"),
		new JLabel("car"),
		new JLabel("cbr")
	};

    private void Init()
    {
        cpu = new CPU();

		open = new JMenuItem("Abrir arquivo");
        open.addActionListener(this);
        compiler = new JMenuItem("Compilar");
        compiler.addActionListener(this);

		slow = new JMenuItem("Devagar");
		slow.addActionListener(cpu);
		step = new JMenuItem("Passo-a-passo");
		step.addActionListener(cpu);
		undo = new JMenuItem("Desfazer");
		undo.addActionListener(cpu);
		restart = new JMenuItem("Reiniciar");
		restart.addActionListener(cpu);

		run = new JMenu("Rodar");
		run.add(slow);
		run.add(step);
		run.add(undo);
		run.add(restart);

		file = new JMenu("Arquivo");
        file.add(open);
        file.add(compiler);

		menuBar = new JMenuBar();
		menuBar.setBounds(0,0,window_width,20);
		menuBar.add(file);
		menuBar.add(run);

		codeText = new JTextArea();
		codeText.setBounds(10, 30, (first_width) - 20, window_height - 80);

		int offsetX = 10,
		offsetY = 30;
        for(int n = 0; n < registers.length; n++)
        {
			registers[n] = new JTextField("00");

			int offset_width = (n%2) * (window_width - first_width)/2 + offsetX + first_width,
			offset_height = (40 * (n/2)) + offsetY;
			
			registerLabels[n].setBounds(offset_width, offset_height, 30, 20);
			registers[n].setBounds(offset_width + 30, offset_height, 20, 20);

			add(registerLabels[n]);
			add(registers[n]);
		}

		flagLabel.setBounds(first_width + 10, 20*registers.length + offsetY, 40, 20);
		add(flagLabel);
		flags.setBounds(first_width + 50, 20*registers.length + offsetY, 30, 20);
		add(flags);

		add(menuBar);
        add(codeText);
	}

    public void actionPerformed(ActionEvent e)
    {
        switch(((JMenuItem)e.getSource()).getText())
        {
            case "Abrir arquivo":
                JFileChooser fc = new JFileChooser();
                int i = fc.showOpenDialog(this);
                
                if(i == JFileChooser.APPROVE_OPTION)
                {
                    File f = fc.getSelectedFile();

                    String code = "";
                    try
                    {
                        Scanner in = new Scanner(f);
        
                        while(in.hasNextLine())
                        {
                            code += in.nextLine() + "\n";
                        }

                        in.close();
                    }catch (Exception ex)
                    {
                        code = "O arquivo não foi encontrado!";
                    }

                    codeText.setText(code);
                }
                break;

            case "Compilar":
                Compilator.compilar((InputStream)new ByteArrayInputStream(codeText.getText().getBytes(StandardCharsets.UTF_8)), cpu.getMEM());
                break;
		}
	}

	public MainFrame() {
		Init();

		setTitle(currLine + " - Interpretador Assembly - " + currTime);
        setSize(window_width, window_height);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
