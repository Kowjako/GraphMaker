import java.awt.Graphics;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/* 
 *     Plik: Graph.java
 *           
 *          Klasa reprezentuje graph
 *           
 *    Autor: Uladzimir Kaviaka(257276)
 *     Data: 19 grudnia 2020 r.
 */

public class Graph implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private List<Node> nodes; /*wierzcholki*/
	private List<Edge> edges; /*krawedzie*/
	
	public Graph() {
		this.nodes = new ArrayList<Node>();	
		this.edges = new ArrayList<Edge>();
	}
	
	public void addNode(Node node){
		nodes.add(node);
	}
	
	public void addEdge(Edge edge) {
		edges.add(edge);
	}
	
	public void removeEdge(Edge edge) {
		edges.remove(edge);
	}
	
	public void removeNode(Node node) {
		for (int i = 0; i < edges.size(); i++) {
			if (edges.get(i).getStartNode() == node || edges.get(i).getEndNode() == node) {
				edges.remove(i);
				i--;
			}
		}
		nodes.remove(node);
	}
	
	public Node[] getNodes(){
		Node [] array = new Node[0];
		return nodes.toArray(array);
	}
	
	public Edge[] getEdges() {
		Edge[] array = new Edge[0];
		return edges.toArray(array);
	}
	
	public void draw(Graphics g){
		for(Edge edge : edges) {
			edge.draw(g);
		}
	    for(Node node : nodes){
			node.draw(g);
		}	
	}

	public Node getByName(String name) {
		for(Node node : nodes) {
			if(node.name.equals(name)) return node;
		}
		return null;
	}
	
	public static void saveToFile(String filename, Graph g) throws Exception {
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
			outputStream.writeObject(g);
		} catch (Exception e) {
			throw new Exception("Blad podczas zapisywania do pliku");
		}
	}
	
	public static Graph readFromFile(String filename) throws Exception {
		try (ObjectInputStream outputStream = new ObjectInputStream(new FileInputStream(filename))) {
			Graph tmp = (Graph)outputStream.readObject();
			return tmp;
		} catch (Exception e) {
			throw new Exception("Blad podczas zapisywania do pliku");
		}
	}
}
