package Lab_12_3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import Lab_12_3.Shape_Panel.Black_dots;
import Lab_12_3.Shape_Panel.Shape_Thread;

public class Controller {
	
	private static volatile boolean isRunning = true;
	private boolean started_flag = false;
	private boolean pause_flag = false;
	private boolean reset_flag = false;

	private Model model;
	private static View view;
	
	private shapes_panel_class shapes_panel = new shapes_panel_class();

	private ArrayList<Shape_Thread> ST_array = new ArrayList<Shape_Thread>();
	private ArrayList<Thread> Threads_array = new ArrayList<Thread>();
	private ArrayList<Model.Black_dots> Black_dots_array = new ArrayList<Model.Black_dots>();
	private Color[] colors = {Color.RED, Color.ORANGE, Color.CYAN, Color.GREEN, Color.PINK };
	
	public Controller(Model m, View v)
	{
		model = m;
		view = v;
		for(int i=0 ;i<view.getnumofPhilosphers(); i++)
		{
			//for black  dots
			double angle = Math.PI/2 + (2*Math.PI/view.getnumofPhilosphers())*(i-0.5);

			Black_dots_array.add(model.new Black_dots(i,(int) (450/1.85 + 500/10.0 * Math.cos(angle)),(int) (450/1.85 - 500/10.0 * Math.sin(angle)) ) );	
			repaint();
		}
		
		for(int i=0; i<view.getnumofPhilosphers(); i++)
		{
			//for main dots 		
			double angle = Math.PI/2 + (2*Math.PI/view.getnumofPhilosphers())*(i);
			
				if (i==view.getnumofPhilosphers()-1) {
					ST_array.add(new Shape_Thread(i,
							(int) (450/2.0 + 500/4.0 * Math.cos(angle) ), (int) (450/2.0 - 500/4.0 * Math.sin(angle) ),
			                Black_dots_array.get((i+1) % view.getnumofPhilosphers()),
			                Black_dots_array.get((i)% view.getnumofPhilosphers()) ));
				}
				else {
					ST_array.add(new Shape_Thread(i,
							(int) (450/2.0 + 500/4.0 * Math.cos(angle) ), (int) (450/2.0 - 500/4.0 * Math.sin(angle)),
	                Black_dots_array.get((i)% view.getnumofPhilosphers()),
	                Black_dots_array.get((i+1) % view.getnumofPhilosphers())) );
				}   
					
				Threads_array.add(new Thread(ST_array.get(i), "Shape#" + (i+1)));		
		}
		
		//Adding Action Listeners
		
		shapes_panel = new shapes_panel_class();
		view.shape_panel_repaint(shapes_panel);
		
		view.getReset().setEnabled(false);
		view.getPause().setEnabled(false);
		
		view.getStart().addActionListener(new ActionListener() 
		{	

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
									
				if(reset_flag == false)
				{
					//if(pause_flag == false) {
					if(started_flag == false)
					{
						pause_flag = false;
						for(int i=0; i<view.getnumofPhilosphers(); i++)
						{
							Threads_array.get(i).start();
						}
						started_flag = true;
						
						view.getStart().setEnabled(false);
						view.getPause().setEnabled(true);
						view.getReset().setEnabled(false);
					}
					else
					{
						pause_flag = false;
						isRunning = true;
						view.getPause().setEnabled(true);
						view.getStart().setEnabled(false);
					}
					//}
				}
				
				else
				{
					for(int i=0;i<view.getnumofPhilosphers();i++) {
						ST_array.get(i).set_circle_color(Color.BLUE);
						Black_dots_array.get(i).setB_color(Color.BLACK);
					}
					repaint();
					
					pause_flag = false;
					isRunning = true;
					
					view.getPause().setEnabled(true);
					view.getStart().setEnabled(false);
				}				
			}
			
		});
		
		view.getQuit().addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				isRunning = false;
				System.exit(0);
			}
		});
		
		view.getPause().addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pause_flag = true;
				
				view.getReset().setEnabled(true);
				view.getStart().setEnabled(true);
				view.getPause().setEnabled(false);
			}
		});
		
		view.getReset().addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				isRunning = false;
				reset_flag = true;
				
				for(int i=0;i<view.getnumofPhilosphers();i++) {
					ST_array.get(i).set_circle_color(Color.BLUE);
					Black_dots_array.get(i).setB_color(Color.BLACK);
				}
				repaint();
				
				view.getReset().setEnabled(false);
				view.getPause().setEnabled(false);
				view.getStart().setEnabled(true);
			}
		});
	}
	
	public class Shape_Thread implements Runnable
	{
		private int Thread_id;
		private Model.Black_dots left_dot, right_dot;
		private double x, y;

		public void generate_black_dots()
		{
			right_dot = Black_dots_array.get(Thread_id%view.getnumofPhilosphers());
			left_dot = Black_dots_array.get((Thread_id+1) % view.getnumofPhilosphers());
		}

		public double get_x()
		{
			return x;
		}

		public double get_y()
		{
			return y;
		}

		private Color circle_color;

		public Color get_circle_color()
		{
			return circle_color;
		}
		
		public void set_circle_color(Color c)
		{
			this.circle_color = c;
		}
		
		public synchronized void check_paused()
		{
			while(pause_flag)
			{
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) { }
			}
		}

		@Override
		public synchronized void run() {
			// TODO Auto-generated method stub
			
			Random rand = new Random();
			
			try{
			while(isRunning)
			{			
				check_paused();
				circle_color = colors[Thread_id];
				repaint();
				
				System.out.println("Thread#" + Thread.currentThread().getName()+ ": Hungry");
				
					synchronized(left_dot)
					{							
						synchronized(right_dot)
						{								
							check_paused();
							
							
								left_dot.setB_color(circle_color);
								right_dot.setB_color(circle_color);
								repaint();
								check_paused();
								
								//Eating
								System.out.println("Thread#" + Thread.currentThread().getName()+ ": Is Eating");
								Thread.sleep(1000 + rand.nextInt(10)*500);
								
								check_paused();
								right_dot.setB_color(Color.BLACK);			
								left_dot.setB_color(Color.BLACK);
								//repaint();
								circle_color = Color.BLUE;
								repaint();
								check_paused();
								System.out.println("Thread#" + Thread.currentThread().getName()+ ": Finished Eating");
						}
					}
					
					Thread.sleep(view.getnumofPhilosphers()*1000 + rand.nextInt(10)*500);
			}
				
			}catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Thread.currentThread().interrupt(); 
				e.printStackTrace();
				return;
			}
		}

		public Shape_Thread(int id, int x_cord, int y_cord, Model.Black_dots left, Model.Black_dots right )
		{
			Thread_id = id;
			this.x=x_cord;
			this.y=y_cord;
			left_dot=left;
			right_dot=right;
			circle_color = Color.BLUE;
			System.out.println(Thread.currentThread().getName()+ ": Blue");

			repaint();
		}
		
		public void draw(Graphics2D g2d) {
			g2d.setColor(circle_color);
			g2d.fillOval((int) this.x, (int) this.y, 50, 50);
		}
	}
	
	public class shapes_panel_class extends JPanel {
		public shapes_panel_class() {super();}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			for(int i=0;i<view.getnumofPhilosphers();i++) {
				ST_array.get(i).draw(g2d);
				Black_dots_array.get(i).draw(g2d);
			}
		}
	}
	
	public void repaint() {
		shapes_panel.repaint();
		view.shape_panel_repaint(shapes_panel);
	}
	
	public void paint(Graphics2D g2d) {
		for(int i=0;i<view.getnumofPhilosphers();i++) {
			ST_array.get(i).draw(g2d);
			Black_dots_array.get(i).draw(g2d);
		}
	}
}
