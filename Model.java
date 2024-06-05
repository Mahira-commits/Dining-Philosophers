package Lab_12_3;

import java.awt.Color;
import java.awt.Graphics2D;

public class Model {
	
	public class Black_dots 
	{
		private int ID;
		private Color b_color;
		private double x, y;
		public int getID() {
			return ID;
		}
		public Color getB_color() {
			return b_color;
		}
		public double getX() {
			return x;
		}
		public double getY() {
			return y;
		}

		public void setID(int iD) {
			ID = iD;
		}
		public void setB_color(Color b_color) {
			this.b_color = b_color;
		}
		public void setX(double x) {
			this.x = x;
		}
		public void setY(double y) {
			this.y = y;
		}

		public Black_dots(int iD, double x, double y) {
			super();
			ID = iD;
			this.b_color = Color.BLACK;
			this.x = x;
			this.y = y;
		}
		
		public void draw(Graphics2D g2d) {
			g2d.setColor(b_color);
			g2d.fillOval((int) x, (int)y, 10, 10);
		}
	}
}
