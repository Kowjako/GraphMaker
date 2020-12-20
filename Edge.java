import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
/* 
 *     Plik: Edge.java
        
         Klasa reprezentuje krawedz grafu
        
 *    Autor: Uladzimir Kaviaka(257276)
 *     Data: 19 grudzien 2020 r.
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
   
   public boolean isMouseOver(int m_posx, int m_posy) {
      double precision = 5; /*maksymalna rozbieznosc pomiedzy myszka a krawedzia */
      if((m_posx<=startNode.getX() || m_posx<=endNode.getX()) && (m_posx>=startNode.getX() || m_posx>=endNode.getX())) { 
    	  if((m_posy>startNode.getY() && m_posy>endNode.getY()) || ((m_posy)<startNode.getY() && m_posy<endNode.getY())) {
    		  return false;  /*myszka nie lezy wewnatrz prostokatu okreslonego wierzcholkami*/
    	  }
			else {
				/*Odleglosc pomiedzy punktem a prosta */
				double distance = (double) Math
						.abs((endNode.getY() - startNode.getY()) * m_posx - (endNode.getX() - startNode.getX()) * m_posy
								+ endNode.getX() * startNode.getY() - endNode.getY() * startNode.getX())
								/ Math.sqrt(Math.pow((endNode.getY() - startNode.getY()), 2)
										+ Math.pow(endNode.getX() - startNode.getX(), 2));
				if (distance <= precision)
					return true;
				else
					return false;
			}
      } else return false; 
   }
   
   public String toString() {
		return "[" + this.startNode.getName() + "->" + this.endNode.getName() + "]" + " Color: " + color.getRed() + " "
				+ color.getGreen() + " " + color.getBlue();
   }
}