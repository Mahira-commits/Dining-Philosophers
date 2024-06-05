package Lab_12_3;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Lab_12_3.Controller.shapes_panel_class;

public class View extends JPanel{
		
	private JFrame frame = new JFrame();
	private JPanel button_panel = new JPanel();
	private JButton start = new JButton("Start");
	private JButton pause = new JButton("Pause");
	private JButton reset = new JButton("Reset");
	private JButton quit = new JButton("Quit");
	
	private String[] choices = { "2", "3", "4","5" };
	
	private int numofPhilosphers;
	
	public void shape_panel_repaint(Controller.shapes_panel_class shapes_p)
	{
		//this.setLayout(new BorderLayout());
		this.add(shapes_p, BorderLayout.CENTER); 
		this.setVisible(true);
	}

	public int getnumofPhilosphers() {
		return numofPhilosphers;
	}

	public JFrame getFrame() {
		return frame;
	}

	public JButton getStart() {
		return start;
	}

	public JButton getQuit() {
		return quit;
	}

	public JButton getPause() {
		return pause;
	}

	public JButton getReset() {
		return reset;
	}

	public View()
	{	
		this.setLayout(new BorderLayout());
		
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		numofPhilosphers = Integer.parseInt( (String) JOptionPane.showInputDialog(null, "Choose the number of philsophers...",
		        "", JOptionPane.QUESTION_MESSAGE, null, choices, choices[1])); // Initial choice
		
		System.out.println(numofPhilosphers);
 	
		start.setFont(new Font("Times New Roman", Font.BOLD, 15));
		start.setAlignmentX(CENTER_ALIGNMENT);
		pause.setFont(new Font("Times New Roman", Font.BOLD, 15));
		pause.setAlignmentX(CENTER_ALIGNMENT);
		reset.setFont(new Font("Times New Roman", Font.BOLD, 15));
		reset.setAlignmentX(CENTER_ALIGNMENT);
		quit.setFont(new Font("Times New Roman", Font.BOLD, 15));
		quit.setAlignmentX(CENTER_ALIGNMENT);
		
		button_panel.setLayout(new FlowLayout());
		button_panel.add(start);
		button_panel.add(pause);	
		button_panel.add(reset);
		button_panel.add(quit);
		
		frame.add(this, BorderLayout.CENTER);
		frame.add(button_panel, BorderLayout.SOUTH);
		frame.setVisible(true);	
	}
}
