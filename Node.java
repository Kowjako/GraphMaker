import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

/* 
 *     Plik: Node.java
        
 *    Autor: Uladzimir Kaviaka
 *     Data: XX grudzien 2020 r.
 */

public class Node implements Serializable {
	
	private static final long serialVersionUID = 1L;
	protected int x;
	protected int y;
	
	protected int r;
	protected String name;
	
	private Color color;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
		this.r = 20;
		this.color = Color.WHITE;
		this.name = "-";
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public boolean isMouseOver(int mx, int my){
		return (x-mx)*(x-mx)+(y-my)*(y-my)<=r*r;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String tmp) {
		if (tmp == null || tmp.equals(""))
			return;
		this.name = tmp;
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x-r, y-r, 2*r, 2*r);
		g.setColor(Color.BLACK);
		g.drawOval(x-r, y-r, 2*r, 2*r);
		g.drawString(name, x-3, y+3);
	}
	
	@Override
	public String toString(){
		return ("(" + x + ", " + y + ", " + r + ") Name: " + name + " Color: " + color.getRed() + " " + color.getGreen()
				+ " " + color.getBlue());
	}

	
}
