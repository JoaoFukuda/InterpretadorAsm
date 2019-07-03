// Contém a janela principal do programa
package src.grafico;

import src.CPU;
import src.auxi.Compilator;
import src.auxi.Hexa;

import java.util.Scanner;
import java.awt.event.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

import javax.swing.*;

public class MainFrame extends JFrame implements ActionListener
{

	private int window_width = 500, window_height = 500,
    first_width = window_width/2;

    CPU cpu = null;

    String currLine;
    int currTime = 0;
	JMenuBar menuBar;
	JMenu file, run;
	JMenuItem open, compiler;
	JMenuItem slow, step, undo, restart;
	JTextArea codeText;
	JTextField flags = new JTextField();
	JLabel flagLabel = new JLabel("flags (ez/s/err)");
	JTextField barrInterno = new JTextField();
	JLabel barrInternoLabel = new JLabel("Barramento Interno");
	JTextField barrExterno = new JTextField();
	JLabel barrExternoLabel = new JLabel("Barramento Externo");
	JTextField busDoor = new JTextField();
	JLabel busDoorLabel = new JLabel("port");
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
		open = new JMenuItem("Abrir arquivo");
        open.addActionListener(this);
        compiler = new JMenuItem("Compilar");
        compiler.addActionListener(this);

		slow = new JMenuItem("Devagar");
		// slow.addActionListener(this);
		step = new JMenuItem("Passo-a-passo");
		step.addActionListener(this);
		undo = new JMenuItem("Desfazer");
		// undo.addActionListener(this);
		restart = new JMenuItem("Reiniciar");
		restart.addActionListener(this);

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
			registers[n] = new JTextField();

			int offset_width = (n%2) * (window_width - first_width)/2 + offsetX + first_width,
			offset_height = (40 * (n/2)) + offsetY;
			
			registerLabels[n].setBounds(offset_width, offset_height, 30, 20);
			registers[n].setBounds(offset_width + 30, offset_height, 30, 20);

			add(registerLabels[n]);
			add(registers[n]);
		}

		flagLabel.setBounds(first_width + 10, 20*registers.length + offsetY, 100, 20);
		add(flagLabel);
		flags.setBounds(first_width + 110, 20*registers.length + offsetY, 30, 20);
		add(flags);

		barrInternoLabel.setBounds(first_width + 10, 20*registers.length + 30 + offsetY, 110, 20);
		add(barrInternoLabel);
		barrInterno.setBounds(first_width + 120, 20*registers.length + 30 + offsetY, 120, 20);
		add(barrInterno);

		barrExternoLabel.setBounds(first_width + 10, 20*registers.length + 60 + offsetY, 110, 20);
		add(barrExternoLabel);
		barrExterno.setBounds(first_width + 120, 20*registers.length + 60 + offsetY, 120, 20);
		add(barrExterno);

		busDoorLabel.setBounds(first_width + 10, 20*registers.length + 90 + offsetY, 50, 20);
		add(busDoorLabel);
		busDoor.setBounds(first_width + 40, 20*registers.length + 90 + offsetY, 200, 20);
		add(busDoor);

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
                cpu = new CPU();
                Compilator.compilar((InputStream)new ByteArrayInputStream(codeText.getText().getBytes(StandardCharsets.UTF_8)), cpu.getMEM());
                Update();
                break;

            case "Devagar":
                System.out.println("Não está funcionando");
                break;

            case "Passo-a-passo":
                cpu.Run();
                Update();
                break;

            case "Desfazer":
                System.out.println("Não está funcionando");
                break;

            case "Reiniciar":
                cpu = new CPU();
                Update();
                break;

            default:
		}
    }

    private void Update()
    {
        int[] tempReg = cpu.getRegisters();
        for(int n = 0; n < registers.length; n++)
        {
            int tes = tempReg[n];

            if(registerLabels[n].getText() == "pc") tes /= 3;

            //registers[n].setText(Hexa.intToString(tes));
            registers[n].setText(""+tes);
        }
        barrInterno.setText(Integer.toHexString(cpu.barramentoInterno.data));
        barrExterno.setText(Integer.toHexString(cpu.barramentoExterno.data));
        currTime = cpu.UC.time;
        currLine = cpu.UC.code;
        String temp = "";
        for(boolean ch : cpu.UC.doors) temp += (ch) ? "1" : "0";
        busDoor.setText(temp);
        flags.setText(cpu.UC.flagsToString());
        setTitle(currLine + " - Interpretador Assembly - T:" + currTime);
    }

	public MainFrame() {
        Init();
        
        setTitle("Interpretador Assembly");
        setSize(window_width, window_height);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
