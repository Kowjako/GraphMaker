import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;
/* 
 *     Plik: Edge.java
        
 *    Autor: Uladzimir Kaviaka
 *     Data: XX grudzien 2020 r.
 */
public class Edge implements Serializable {
   
   private static final long serialVersionUID = 1L;
   private Node startNode;
   private Node endNode;
   private Color color;

   public Edge(Node startNode, Node endNode) {
      this.startNode = startNode;
      this.endNode = endNode;
      this.color = Color.BLACK;
   }

   public Node getStartNode() {
      return startNode;
   }

   public Node getEndNode() {
      return endNode;
   }
   
   public void setColor(Color tmp) {
	   color = tmp;
   }
   
   public Color getColor() {
	   return color;
   }

   void draw(Graphics g) {
	   g.setColor(color);
	   g.drawLine(startNode.getX(), startNode.getY(), endNode.getX(), endNode.getY());
   }

   /*Piewszy if - czy nalezy do prostokatu na osi Ox oraz nie ma roznicy pomiedzy startNode oraz endNode */
   /*Drugi if - sprawdza czy nalezy do prostokatu na osi Oy */
   
   public boolean isMouseOver(int m_posx, int m_posy) {
      double precision = 5; /*maksymalna rozbieznosc pomiedzy myszka a krawedzia */
      if((m_posx<=startNode.getX() || m_posx<=endNode.getX()) && (m_posx>=startNode.getX() || m_posx>=endNode.getX())) { 
    	  if(m_posy>startNode.getY() && m_posy>endNode.getY() || (m_posy)<startNode.getY() && m_posy<endNode.getY()) {
    		  return false;  /*myszka nie lezy wewnatrz prostokatu */
    	  }
    	  else {
    		  /*zmienne do obliczania odleglosci pomiedzy prosrtymi rownoleglymi */
    		  int x = endNode.getY() - startNode.getY(); 
    		  int y = startNode.getX() - endNode.getX();
    		  int z = endNode.getX() * startNode.getY() - startNode.getX() * endNode.getY();
    		  double tmp_precision = (double)Math.abs(x*m_posx+y*m_posy+z)/Math.sqrt(x*x+y*y);
    		  if(tmp_precision<=precision) return true;
    		  else return false;
    	  }
      } else return false; 
   }
   
   public String toString() {
		return "[" + this.startNode.getName() + "->" + this.endNode.getName() + "]" + " Color: " + color.getRed() + " "
				+ color.getGreen() + " " + color.getBlue();
   }
}